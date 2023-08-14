create table Stocks(
                       id serial primary key,
                       symbol varchar(200),
                       exchange varchar(200),
                       exchange_suffix varchar(200),
                       exchange_name varchar(200),
                       exchange_segment varchar(200),
                       exchange_segment_name varchar(200),
                       name varchar(200),
                       date varchar(200),
                       type varchar(200),
                       iex_id varchar(200),
                       region varchar(200),
                       currency varchar(200),
                       is_enabled varchar(200),
                       figi varchar(200),
                       cik varchar(200),
                       lei varchar(200)
)