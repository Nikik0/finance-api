create table Stocks(
    id serial primary key,
    avg_total_volume bigint,
    calculation_price varchar(200),
    change float,
    change_percent float,
    close float,
    close_source varchar(200),
    close_time bigint,
    company_name varchar(200),
    currency varchar(200),
    delayed_price float,
    delayed_price_time bigint,
    extended_change float,
    extended_change_percent float,
    extended_price float,
    extended_price_time bigint,
    high float,
    high_source varchar(200),
    high_time bigint,
    iex_ask_price float,
    iex_ask_size float,
    iex_bid_price float,
    iex_bid_size float,
    iex_close float,
    iex_close_time bigint,
    iex_last_updated bigint,
    iex_market_percent float,
    iex_open float,
    iex_open_time bigint,
    iex_realtime_price float,
    iex_realtime_size float,
    iex_volume bigint,
    last_trade_time bigint,
    latest_price float,
    latest_source varchar(200),
    latest_time varchar(200),
    latest_update bigint,
    latest_volume bigint,
    low float,
    low_source varchar(200),
    low_time bigint,
    market_cap bigint,
    odd_lot_delayed_price float,
    odd_lot_delayed_price_time bigint,
    open float,
    open_time bigint,
    open_source varchar(200),
    pe_ratio float,
    previous_close float,
    previous_volume bigint,
    primary_exchange varchar(200),
    symbol varchar(200),
    volume bigint,
    week52_high float,
    week52_low float,
    ytd_change float,
    is_US_market_open boolean
);
create table company(
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