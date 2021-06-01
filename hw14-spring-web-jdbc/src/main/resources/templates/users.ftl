<#import "parts/common.ftl" as c>
<@c.page>
    <div class="container" mt-3>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <td scope="col">Id</td>
                    <td scope="col">Name</td>
                    <td scope="col">Login</td>
                    <td scope="col">Password</td>
                    <td scope="col">Street</td>
                    <td scope="col">Phone</td>
                </tr>
                </thead>
                <tbody>
                <#list allUsers as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td>${user.addressDataSet.street}</td>
                        <td>
                            <#list  user.phoneDataSets as phone>
                                ${phone.getNumber()} <#sep>,
                            </#list>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div>
            <a href="/addClient" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Add client</a>
        </div>
    </div>
</@c.page>
