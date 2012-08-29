CREATE
    OR REPLACE
  VIEW v_user_group
    AS SELECT username
            , groupname 
         FROM user_group 
         JOIN account_user 
           ON user_group.account_user_id = account_user.id;
