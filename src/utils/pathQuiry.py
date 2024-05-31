import networkx as nx
import psycopg2

conn = psycopg2.connect(
    dbname="project",
    user="checker",
    password="123456",
    host="localhost",
)
c = conn.cursor()
c.execute("SELECT chinese_name, position FROM line_station ls JOIN station s ON ls.station_id = s.id ORDER BY ls.id;")
data = c.fetchall()
G = nx.Graph()

# 添加地铁站之间的路径
stations = [item[0] for item in data]
positions = [item[1] for item in data]
for i in range(len(stations)):
    if not G.has_node(stations[i]):
        G.add_node(stations[i])
    if i > 0 and positions[i] - 1 == positions[i - 1]:
        G.add_edge(stations[i], stations[i - 1])

for start_station in G.nodes:
    for end_station in G.nodes:
        if start_station != end_station:
            try:
                path = nx.shortest_path(G, start_station, end_station)
                c.execute("SELECT id FROM station WHERE chinese_name = %s", (start_station,))
                start_station_id = c.fetchone()[0]
                c.execute("SELECT id FROM station WHERE chinese_name = %s", (end_station,))
                end_station_id = c.fetchone()[0]
                c.execute("INSERT INTO path (start_station_id, end_station_id, path) VALUES (%s, %s, %s) ON CONFLICT DO NOTHING",
                          (start_station_id, end_station_id, '->'.join(path)))
            except nx.NetworkXNoPath:
                pass

conn.commit()
conn.close()