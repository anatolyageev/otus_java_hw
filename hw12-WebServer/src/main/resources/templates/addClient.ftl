<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container">
        <div class="col-md-8 col-md-offset-2">
            <h3>Add new user</h3>
            <form  method="POST" action="/addClient">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputLogin">Login</label>
                        <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Login">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPassword4">Password</label>
                        <input type="password" class="form-control" id="inputPassword4" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div>
                                <label for="inputName">Name</label>
                               <input type="text" class="form-control" id="inputName" name="name" placeholder="Name">
                    </div>

                </div>
<#--              -->
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>
    </div>

</@c.page>