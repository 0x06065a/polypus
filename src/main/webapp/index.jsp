<!DOCTYPE html>
<html ng-app="polypusModule">
  <head>
    <meta charset="utf-8"/>
    <title>polypus</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <base href="${pageContext.request.contextPath}/"/>

    <link href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/style.css">
  </head>
  <body ng-controller="journalsController" style="background-image: url('resources/img/bgnd.jpg')">
    <header class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <p class="navbar-text">{{date.current | date:'dd.MM.yyyy'}}</p>
        </div>
        <form ng-submit="loadDesk()" class="navbar-form navbar-right">
          <div class="form-group">
            <input id="user-id" type="text" placeholder="Enter journal ID..." ng-model="journal.name"
                   class="form-control input-sm">
          </div>
        </form>
      </div>
    </header>
    <button id="menu" ng-show="journal.id" class="btn btn-danger"><span class="glyphicon glyphicon-th"></span>
    </button>
    <div class="container">
      <ul id="tasks" ng-controller="tasksController">
        <li ng-repeat="task in journal.tasks" class="task">
          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="panel-title">{{task.name}}
                <div showonparenthover class="task-controls">
                  <button class="btn btn-success btn-xs"><span class="glyphicon glyphicon-ok"></span>
                  </button>
                  <button class="btn btn-danger btn-xs"><span
                      class="glyphicon glyphicon-remove"></span></button>
                </div>
              </div>
            </div>
            <table class="table">
              <tbody>
                <tr ng-repeat="step in task.steps">
                  <td class="step">
                    <div class="step-title">{{step.name}}
                      <div showonparenthover class="step-controls">
                        <button class="btn btn-warning btn-xs"><span
                            class="glyphicon glyphicon-log-out"></span></button>
                        <button class="btn btn-success btn-xs"><span
                            class="glyphicon glyphicon-ok"></span></button>
                        <button class="btn btn-danger btn-xs"><span
                            class="glyphicon glyphicon-remove"></span></button>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td class="new-step" ng-controller="stepsController">
                    <form ng-submit="addStep()">
                      <input type="text" placeholder="Add step..." class="form-control input-sm" ng-model="newStepName">
                    </form>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </li>
        <li id="new-task" ng-show="journal.id" class="task">
          <form ng-submit="addTask()" class="panel panel-default">
            <div class="panel-heading">
              <input type="text" placeholder="Add task..." class="form-control input-sm" ng-model="newTaskName">
            </div>
            <div class="panel-body"></div>
          </form>
        </li>
      </ul>
    </div>

    <script type="text/javascript" src="resources/bower_components/angular/angular.min.js"></script>
    <script type="text/javascript" src="resources/bower_components/angular-cookies/angular-cookies.min.js"></script>
    <script type="text/javascript"
            src="resources/bower_components/angular-resource/angular-resource.min.js"></script>

    <script type="text/javascript" src="resources/js/polypus.js"></script>
    <script type="text/javascript" src="resources/js/services.js"></script>
    <script type="text/javascript" src="resources/js/controllers.js"></script>
  </body>
</html>