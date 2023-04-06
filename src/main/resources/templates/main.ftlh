<#import "parts/common.ftlh" as c>


<@c.page>
<div class="row">
    <div class="col-sm-6">
        <div class="card">
            <div class="card-body">

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="post" action="filter" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?if_exists}" placeholder="Поиск по тегу">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button class="btn btn-primary ml-2" type="submit">Найти</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить нового студента
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите ФИО"/>
                </div>

                <div class="form-group">
                    <input type="text" name="groupp" class="form-control" placeholder="Введите номер группы"/>
                </div>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customfile">
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Добавить</button>
                </div>

        </form>
    </div>
        </div>
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="card">
            <div class="card-body">

                <div class="card my-3" style="width: 18rem;"><h3>Список студентов</h3></div>
                    <div class="card-columns">
                        <#list students as student>
                        <div class="m-2">
                            <b>${student.getGroupp()}</b>
                            <span>${student.getName()}</span>
                            <strong>${student.getStudentuser()}</strong>
                        </div>
                        <#else>
                            Список пуст
                        </#list>
                    </div>
            </div>
        </div>
    </div>
    <div>

</@c.page>




