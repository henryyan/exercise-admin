
    alter table t_admin_role_authority 
        drop foreign key FKAE243466DE3FB930;

    alter table t_admin_role_authority 
        drop foreign key FKAE2434663FE97564;

    alter table t_admin_user_role 
        drop foreign key FKFE85CB3EDE3FB930;

    alter table t_admin_user_role 
        drop foreign key FKFE85CB3E836A7D10;

    drop table if exists t_admin_authority;

    drop table if exists t_admin_role;

    drop table if exists t_admin_role_authority;

    drop table if exists t_admin_user;

    drop table if exists t_admin_user_role;

    create table t_admin_authority (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_admin_role (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_admin_role_authority (
        role_id bigint not null,
        authority_id bigint not null
    ) ENGINE=InnoDB;

    create table t_admin_user (
        id bigint not null auto_increment,
        email varchar(255),
        login_name varchar(255) not null unique,
        name varchar(255),
        password varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table t_admin_user_role (
        user_id bigint not null,
        role_id bigint not null
    ) ENGINE=InnoDB;

    alter table t_admin_role_authority 
        add constraint FKAE243466DE3FB930 
        foreign key (role_id) 
        references t_admin_role (id);

    alter table t_admin_role_authority 
        add constraint FKAE2434663FE97564 
        foreign key (authority_id) 
        references t_admin_authority (id);

    alter table t_admin_user_role 
        add constraint FKFE85CB3EDE3FB930 
        foreign key (role_id) 
        references t_admin_role (id);

    alter table t_admin_user_role 
        add constraint FKFE85CB3E836A7D10 
        foreign key (user_id) 
        references t_admin_user (id);
