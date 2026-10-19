use zenon_frauds;

create table transactions (
                              id bigint auto_increment primary key,
                              step int not null,
                              type varchar(20) not null,
                              amount decimal(20,2) not null,
                              name_origin varchar(50) not null,
                              old_balance_origin decimal(20,2) not null,
                              new_balance_origin decimal(20,2) not null,
                              name_recipient varchar(50) not null,
                              old_balance_recipient decimal(20,2) not null,
                              new_balance_recipient decimal(20,2) not null,
                              is_fraud tinyint(1) default 0,
                              is_flagged_fraud tinyint(1) default 0
);



insert into transactions (
    step,
    type,
    amount,
    name_origin,
    old_balance_origin,
    new_balance_origin,
    name_recipient,
    old_balance_recipient,
    new_balance_recipient,
    is_fraud,
    is_flagged_fraud
) values
      (1, 'PAYMENT',  9839.64, 'C1231006815', 170136.00, 160296.36, 'M1979787155',      0.00,       0.00, 0, 0),
      (1, 'PAYMENT',  1864.28, 'C1666544295',  21249.00,  19384.72, 'M2044282225',      0.00,       0.00, 0, 0),
      (1, 'TRANSFER',   181.00, 'C1305486145',    181.00,      0.00, 'C553264065',       0.00,       0.00, 1, 0),
      (1, 'CASH_OUT',   181.00, 'C840083671',     181.00,      0.00, 'C38997010',    21182.00,       0.00, 1, 0),
      (1, 'PAYMENT', 11668.14, 'C2048537720',  41554.00,  29885.86, 'M1230701703',      0.00,       0.00, 0, 0);
