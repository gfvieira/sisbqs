<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="src.modelo.Usuario"%>
<%@page import="java.io.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%Usuario usuario = (Usuario) session.getAttribute("usuario");
    Timestamp tm = new Timestamp(System.currentTimeMillis());
    String year = new SimpleDateFormat("yyyy").format(tm);
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">

        <title>= SisBQS =</title>
        <!--<link rel="shortcut icon" href="assets/images/gt_favicon.png">-->
        <!-- Bootstrap core CSS -->
        <link href="assets3/css/bootstrap.css" rel="stylesheet">
        <!--external css-->
        <link href="assets3/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="assets3/js/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="assets3/css/style.css" rel="stylesheet">
        <link href="assets3/css/style-responsive.css" rel="stylesheet">
        <script language=javascript type="text/javascript">
            dayName = new Array("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
            monName = new Array("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro");
            now = new Date;
        </script>
        <script src="assets3/js/time_session.js" type="text/javascript"></script>



        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body onload="time()">

        <section id="container" >
            <!-- **********************************************************************************************************************************************************
            TOP BAR CONTENT & NOTIFICATIONS
            *********************************************************************************************************************************************************** -->
            <!--header start-->
            <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="#" class="logo"><b>SisBQS</b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
<!--                        <li>
                            <div style="margin-top: 22px; margin-right: 20px; color: white">
                                <div id="timeout"></div>
                            </div>
                        </li>-->
                        <li>
                            <div class="btn-group" style="margin-top: 15px">
                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fa fa-cog"></i> <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-right">
                                    <li><a href="#">Perfil</a></li>
                                    <li><a href="#">Alterar Senha</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="/sigbase/LogOff.jsp">Sair</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>

            </header>
            <!--header end-->

            <!-- **********************************************************************************************************************************************************
            MAIN SIDEBAR MENU
            *********************************************************************************************************************************************************** -->
            <!--sidebar start-->
            <aside>
                <div id="sidebar"  class="nav-collapse ">
                    <ul class="sidebar-menu" id="nav-accordion">
                        <h5 class="centered"><%out.print(usuario.getNome());%></h5>
                        <h5 class="centered"> 
                            <script language=javascript type="text/javascript">
                                document.write(dayName[now.getDay() ] + ", " + now.getDate() + " de " + monName [now.getMonth() ] + " de " + now.getFullYear());
                            </script>
                        </h5>
                        <h5 class="centered"><div id="txt"></div></h5>
                        <li class="mt">
                            <a class="active" href="home.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Principal</span>
                            </a>
                        </li>   
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-users"></i>
                                <span>Alunos</span>
                            </a>
                            <ul class="sub">
                                <li><a href="aluno/cadastroAluno.jsp">Cadastrar</a></li>
                                <li><a href="aluno/ListAluno.jsp">Listar</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-calendar"></i>
                                <span>Agenda</span>
                            </a>
                            <ul class="sub">
                                <li><a href="agenda/cadastroAgenda.jsp">Inserir Evento</a></li>
                                <li><a href="agenda/ListAgenda.jsp">Listar  Evento</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-question-circle"></i>
                                <span>Questões</span>
                            </a>
                            <ul class="sub">
                                <li><a href="questao/cadastroQuestao.jsp">Criar</a></li>
                                <li><a href="questao/ListQuestao.jsp">Listar</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-list"></i>
                                <span>Simulado</span>
                            </a>
                            <ul class="sub">
                                <li><a href="simulado/Simulado.jsp">Gerar</a></li>
                                <li><a href="#">Listar</a></li>
                                <li><a href="simulado/resultadoSimulado.jsp">Resultado</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-bar-chart-o"></i>
                                <span>Indicadores</span>
                            </a>
                            <ul class="sub">
                                <li><a href="#">Aluno</a></li>
                                <li><a href="indicador/indicadorDisciplina.jsp">Disciplina</a></li>
                            </ul>
                        </li> 
                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>
            <!--sidebar end-->

            <!-- **********************************************************************************************************************************************************
            MAIN CONTENT
            *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <!-- page start-->
                    <div class="row mt">
                        <div class="col-lg-12 mt">
                            <section class="panel">
                                <div class="panel-body">
                                    <div id="calendar" class="has-toolbar"></div>
                                </div>
                            </section>
                        </div>
                    </div>

                    <!-- page end-->
                </section><!-- /MAIN CONTENT -->

                <!--main content end-->
                <!--footer end-->
            </section>

            <!-- js placed at the end of the document so the pages load faster -->
            <script src="assets3/js/jquery.js"></script>
            <script src="assets3/js/jquery-ui-1.9.2.custom.min.js"></script>
            <script src="assets3/js/fullcalendar/fullcalendar.min.js"></script>    
            <script src="assets3/js/bootstrap.min.js"></script>
            <script class="include" type="text/javascript" src="assets3/js/jquery.dcjqaccordion.2.7.js"></script>
            <script src="assets3/js/jquery.scrollTo.min.js"></script>
            <script src="assets3/js/jquery.nicescroll.js" type="text/javascript"></script>


            <!--common script for all pages-->
            <script src="assets3/js/common-scripts.js"></script>

            <script type='text/javascript'>
                $(document).ready(function () {
                    $('#calendar').fullCalendar({
                        header: {
                            left: 'prev,next today',
                            center: 'title',
                            right: 'agendaWeek,month,agendaDay'

                        },
                        editable: false,
                        axisFormat: 'H:mmtt',
                        slotMinutes: 10,
                        firstHour: 8,
                        minTime: 8,
                        maxTime: 22,
                        monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                        monthNamesShort: ['jan.', 'fev.', 'mar', 'abr', 'mai', 'jun', 'jul.', 'ago', 'set', 'out', 'nov', 'dez'],
                        dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
                        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
                        buttonText: {
                            today: 'Hoje',
                            day: 'dia',
                            week: 'Semana',
                            month: 'Mês'
                        },
                        events: function (start, end, callback) {
                            $.ajax({
                                cache: true,
                                url: '../bqs/Calendario.jsp',
                                dataType: "json",
                                data: {month: start.getMonth(), year: start.getYear() + 1900},
                                success: function (response) {
                                    var myevents = [];
                                    $.each(response, function (i, task) {
                                        myevents.push({
                                            start: task.start,
                                            end: task.end,
                                            allDay: false,
                                            title: task.title,
                                            color: task.backgroundColor
                                        });
                                    });
                                    callback(myevents);
                                },
                                error: function () {
                                    alert('There was an error while fetching events!');
                                }
                            });

                        },
                        backgroundColor: 'green',
                        borderColor: 'green',
                        textColor: 'yellow'
                    });
                });

            </script>
        </section>
    </body>
</html>
