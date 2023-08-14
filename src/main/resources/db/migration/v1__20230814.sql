create table Stocks(
                       id serial primary key,
                       symbol varchar(50),
                       exchange varchar(50),
                       exchange_suffix varchar(50),
                       exchange_name varchar(50),
                       exchange_segment varchar(50),
                       exchange_segment_name varchar(50),
                       name varchar(50),
                       date varchar(50),
                       type varchar(50),
                       iex_id varchar(50),
                       region varchar(50),
                       currency varchar(50),
                       is_enabled varchar(50),
                       fiql varchar(50),
                       cik varchar(50),
                       lei varchar(50)
)