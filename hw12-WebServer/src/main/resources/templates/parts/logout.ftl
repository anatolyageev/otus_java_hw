<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>

<#macro login>
    <li class="dropdown">
        <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-user-o"></i> Login</a>
        <ul class="dropdown-menu">
            <li>
                <form class="form-inline login-form" action="/login" method="post">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="username" placeholder="Username" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" class="form-control" name="password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </li>
        </ul>
    </li>
</#macro>
