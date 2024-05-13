create table user_subscribed_products(
    user_id int references _user(id) on delete cascade,
    product_id int references product(id) on delete cascade
)