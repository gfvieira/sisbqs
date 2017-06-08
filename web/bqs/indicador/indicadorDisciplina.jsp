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
        <link href="../assets3/css/bootstrap.css" rel="stylesheet">
        <!--external css-->
        <link href="../assets3/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="../assets3/css/zabuto_calendar.css">
        <link rel="stylesheet" type="text/css" href="../assets3/js/gritter/css/jquery.gritter.css" />
        <link rel="stylesheet" type="text/css" href="../assets3/lineicons/style.css"> 

        <link rel="stylesheet" href="../assets3/css/morris-0.4.3.min.css">  

        <!-- Custom styles for this template -->
        <link href="../assets3/css/style.css" rel="stylesheet">
        <link href="../assets3/css/style-responsive.css" rel="stylesheet">

        <script src="../assets3/js/chart-master/Chart.js"></script>
        <script language=javascript type="text/javascript">
            dayName = new Array("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
            monName = new Array("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro");
            now = new Date;
        </script>
        <script src="../assets3/js/time_session.js" type="text/javascript"></script>



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
                                <li><a href="#">Disciplina</a></li>
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
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <form class="form-horizontal style-form">
                                    <div class="form-group">
                                        <label class="col-sm-1 col-sm-2 control-label">Aluno:</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" size="1" name="aluno" id="aluno" required>
                                                <option value="">Selecione!</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Intervalos:</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" size="1" name="inter" id="inter" required>
                                                <option value="">Selecione!</option>
                                                <option value="12">2 Primeiros Simulados</option>
                                                <option value="13">3 Primeiros Simulados</option>
                                                <option value="14">4 Primeiros Simulados</option>
                                                <option value="41">4 Últimos Simulados</option>
                                                <option value="31">3 Últimos Simulados</option>
                                                <option value="21">2 Últimos Simulados</option>
                                                <option value="1">Último Simulado</option>
                                                <option value="99">Todos os Simulados</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div> 
                        </div>
                    </div>  
                    <div class="row mt">
                        <!-- page start-->
                        <div id="morris">
                            <div class="col-lg-12">
                                <div class="content-panel">
                                    <div class="border-head">
                                        <h3>Disciplina X Acerto</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div id="hero-graph2" class="graph"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- page end-->
                </section><!-- /MAIN CONTENT -->

                <!--main content end-->
            </section>

            <!-- js placed at the end of the document so the pages load faster -->
            <!-- js placed at the end of the document so the pages load faster -->
            <<script src="../assets3/js/jquery.js"></script>
            <script src="../assets3/js/jquery-1.8.3.min.js"></script>
            <script src="../assets3/js/bootstrap.min.js"></script>
            <script class="include" type="text/javascript" src="../assets3/js/jquery.dcjqaccordion.2.7.js"></script>
            <script src="../assets3/js/jquery.scrollTo.min.js"></script>
            <script src="../assets3/js/jquery.nicescroll.js" type="text/javascript"></script>
            <script src="../assets3/js/jquery.sparkline.js"></script>

            <!--common script for all pages-->
            <script src="../assets3/js/common-scripts.js"></script>

            <script type="text/javascript" src="../assets3/js/gritter/js/jquery.gritter.js"></script>
            <script type="text/javascript" src="../assets3/js/gritter-conf.js"></script>	

            <script src="../assets3/js/grafico/raphael-min.js"></script>
            <script src="../assets3/js/grafico/morris-0.4.3.min.js"></script>
            <script>
                                $(document).ready(function (e) {
                                    var json_data1 = (function () {
                                        var json3;
                                        $.ajax({
                                            type: 'GET',
                                            url: '/sisbqs/bqs/aluno/BuscaAluno.jsp',
                                            async: false,
                                            global: false,
                                            success: function (data) {
                                                $.each(data, function () {
                                                    $("#aluno").append('<option value="' + this.matricula + '">' + this.nome + '</option>')
                                                })
                                            },
                                            error: function () {
                                                alert("Ocorreu um erro ao acessar a base de dados!");
                                            }
                                        });
                                        return json3;
                                    })();
                                    $("body").delegate("#inter", "change", function (data) {
                                        var aluno = $("#aluno").val();
                                        var intervalo = $("#inter").val();
                                        if (aluno === "") {
                                            alert("Selecione primeiro Aluno antes de selecionar um intervalor!!!");
                                        } else {
                                            var json_data2 = (function () {
                                                var json3;
                                                $.ajax({
                                                    type: 'GET',
                                                    url: '/sisbqs/bqs/indicador/IndicadorDisciplina.jsp',
                                                    data: {aluno: aluno, inter: intervalo},
                                                    async: false,
                                                    global: false,
                                                    beforeSend: function () {
                                                        $("#hero-graph2").html("");
                                                    },
                                                    success: function (data) {
                                                        json3 = data;
                                                    },
                                                    error: function () {
                                                        alert("Ocorreu um erro ao desenhar o gráfico OU intervalo não existente!!");
                                                        $("#hero-graph2").html("");
                                                    }
                                                });
                                                return json3;
                                            })();
                                        }
                                        new Morris.Bar({
                                            element: 'hero-graph2',
                                            data: json_data2,
                                            parseTime: false,
                                            xkey: 'disciplina',
                                            ykeys: ['acerto', 'erro', 'duvida'],
                                            barRatio: 0.4,
                                            xLabelAngle: 35,
                                            hideHover: 'auto',
                                            labels: ['Acerto', 'Erro', 'Duvida'],
                                            barColors: ['#0000FF', '#FF0000', '#00FF00']

                                        });
                                    });
                                });
            </script>

        </section>
    </body>
</html>
