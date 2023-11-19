insert into orders (id, created_at, identity_name, buyer_name, user_id)
values (1, now(), '#1019', 'cyj', 1);

insert into order_items (id, created_at, name, unit_price, qty, product_id)
values (next value for order_items_seq, now(), 'namecard', 11.1, 1, 1);
insert into order_items (id, created_at, name, unit_price, qty, product_id)
values (next value for order_items_seq, now(), 'testcard', 22.1, 2, 2);