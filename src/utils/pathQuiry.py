
import pandas as pd
import networkx as nx
import psycopg2

# 读取xlsx表格
data = pd.read_excel('Price.xlsx')

# 创建一个有向图
G = nx.Graph()

# 添加地铁站之间的路径
stations = data.iloc[2:, 2].tolist()  # 读取第三行的数据，即站点名称
positions = data.iloc[2:, 1].values  # 读取第四行及之后的数据，即站点之间的距离
for i in range(len(stations)):
    if not G.has_node(stations[i]):
        G.add_node(stations[i])
    if i > 0 and positions[i] - 1 == positions[i - 1]:
        G.add_edge(stations[i], stations[i - 1])

conn = psycopg2.connect(
    dbname="project",
    user="checker",
    password="123456",
    host="localhost",
)
c = conn.cursor()

# 获取各站到各站的路径并存入数据库
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

            # 提交更改并关闭连接
conn.commit()
conn.close()