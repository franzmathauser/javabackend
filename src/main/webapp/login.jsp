<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login!</h1>
        
        <form action="j_security_check" method="post">
          <fieldset>
            <legend>Login</legend>
                          
            <div>
              <label for="email">Email</label> 
              <input type="text" id="j_username" name="j_username"/>
            </div>
            <div>
              <label for="password">Password</label> 
              <input type="password" id="j_password" name="j_password"/>
            </div>
              
            <div class="buttonRow">
              <input type="submit" value="Login" />
            </div>
            
            </fieldset>
        </form> 
    </body>
</html>
