create table if not exists path (
    id serial primary key ,
    start_station_id integer,
    end_station_id integer ,
    path text ,
    constraint fk_ps_s foreign key (start_station_id) references station(id),
    constraint fk_ps_e foreign key (end_station_id) references station(id)
);
create view pr_v as
select name, id_number, phone_number, gender, district, start_station_id, end_station_id, price, start_time, end_time, carriage from ride r
    join passenger_ride pr on r.id = pr.ride_id
    join passenger p on pr.passenger_id = p.id;
create view cr_v as
select code, start_station_id, end_station_id, price, start_time, end_time, carriage from ride r
    join card_ride cr on r.id = cr.ride_id
    join card c on c.id = cr.card_id;
create view bus_v as
select distinct bus_name, bus_stop_name, chinese_name from bus b join public.bus_to_stop bts on b.id = bts.bus_id
join public.bus_stop bs on bs.id = bts.bus_stop_id
join public.out_bus_stop obs on bs.id = obs.bus_stop_id
join public.out o on obs.out_id = o.id
join station s on o.station_id = s.id;