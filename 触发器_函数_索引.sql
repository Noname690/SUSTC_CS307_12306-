
create trigger people_trigger
    before  insert or update
    on users
    for each row
execute procedure valid_check();

create or replace  function valid_check()
    returns trigger
as
$$
declare
    id varchar(18);
    phone_number varchar;
    year decimal;
    mouth decimal;
    day decimal;
    birthday varchar;
    tmp date;
    i integer;
    w integer;
    checksum integer;
    sum integer;
    ecp integer;
begin
    id=new.id_card_number;
    phone_number=new.phone_number;
    if(length(id))!=18 then RAISE EXCEPTION  'wrong id';
    end if;
    birthday=substr(id,7,8);
    year = substr(birthday,1,4) ;
--     mouth = substr(birthday,5,2);
    day = substr(birthday,7,2);
    if cast (day as numeric)<1 then ecp=2;
    end if;
--     birthday=year||mouth||day;
    tmp=to_date(birthday,'yyyymmdd');
    if year<'1900' then ecp=1;
    end if;
    i=1;
    sum=0;
    while i<18
        loop
            w=2^(18-i);
            w= w%11;
            sum=sum+w*cast(substr(id,i,1)as NUMERIC);
            i=i+1;
        end loop;
    checksum=(12-(sum % 11)) % 11;
    if substr(id,18,1)!='X' then
        if checksum!=cast(substr(id,18,1)as NUMERIC) then ecp=4;
        end if;
    else if checksum!=10 then ecp=4;
    end if;
    end if;
    if length(phone_number)!=11 then ecp=6;
    end if;
    if ecp=1 then RAISE EXCEPTION  'year out of range';
    elseif ecp=2 then RAISE EXCEPTION  'date error';
    elseif ecp=4 then RAISE EXCEPTION  'check_sum does not match';
    elseif ecp=5 then RAISE EXCEPTION  'address incorrect';
    elseif ecp=5 then RAISE EXCEPTION  'phone_number error';

    else return new;
    end if;
end
$$
    language plpgsql;

create index index_name on train_station (train_name,station_name);
create index index_user on users (user_id,password);