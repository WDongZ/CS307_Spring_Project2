
create trigger line_station_insert after insert on line_station for each row execute procedure lsiFunc();
create or replace function lsiFunc()
returns trigger as
    $$
    begin
        update line_station
        set position = position + 1
        where line_id = new.line_id and position >= new.position and station_id != new.station_id;
        return new;
    end;
    $$ language plpgsql;
create trigger line_station_delete after delete on line_station for each row execute procedure lsdFunc();
create or replace function lsdFunc()
returns trigger as
    $$
    begin
        update line_station
        set position = position - 1
        where line_id = old.line_id and position > old.position;
        return old;
    end;
    $$ language plpgsql;
create table if not exists passenger_on(
    id serial primary key ,
    station_id int ,
    start_time timestamp ,
    passenger_id int ,
    constraint fk_po_si foreign key (station_id) references station(id),
    constraint fk_po_pid foreign key (passenger_id) references passenger(id)
);
create table if not exists card_on(
    id serial primary key ,
    station_id int ,
    start_time timestamp,
    card_id int ,
    constraint fk_po_si foreign key (station_id) references station(id),
    constraint fk_po_pid foreign key (card_id) references card(id)
);
create table if not exists price(
    id serial primary key ,
    start_station int ,
    end_station int ,
    price int ,
    constraint fk_p_ss foreign key (start_station) references station(id),
    constraint fk_p_es foreign key (end_station) references station(id),
    constraint uk_se_st unique (start_station,end_station)
);
create trigger passenger_ride_insert after insert on passenger_ride for each row execute procedure priFunc();
create or replace function priFunc()
returns trigger as
    $$
    begin
        delete from passenger_on where passenger_id = new.passenger_id;
        return new;
    end;
    $$ language plpgsql;
create trigger card_ride_insert after insert on card_ride for each row execute procedure criFunc();
create or replace function criFunc()
returns trigger as
    $$
    begin
        delete from card_on where card_id = new.card_id;
        return new;
    end;
    $$ language plpgsql;
