<%@page import="src.modelo.DisciplinaSimulado"%>
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
    String temp = (String) request.getAttribute("mensagem");
    ArrayList<DisciplinaSimulado> list = (ArrayList<DisciplinaSimulado>) request.getAttribute("array");%>
<%Timestamp tm = new Timestamp(System.currentTimeMillis());
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
        <link href="../assets3/js/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="../assets3/css/style.css" rel="stylesheet">
        <link href="../assets3/css/style-responsive.css" rel="stylesheet">
        <script language=javascript type="text/javascript">
            dayName = new Array("Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado");
            monName = new Array("janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro");
            now = new Date;
        </script>
        <!--        <script src="../assets3/js/time_session.js" type="text/javascript"></script>-->



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
                            <a class="active" href="../home.jsp">
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
                                <li><a href="cadastroAluno.jsp">Cadastrar</a></li>
                                <li><a href="ListAluno.jsp">Listar</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-calendar"></i>
                                <span>Agenda</span>
                            </a>
                            <ul class="sub">
                                <li><a href="#">Inserir Evento</a></li>
                                <li><a href="#">Listar  Evento</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-question-circle"></i>
                                <span>Questões</span>
                            </a>
                            <ul class="sub">
                                <li><a href="cadastroQuestao.jsp">Criar</a></li>
                                <li><a href="#">Disciplina</a></li>
                                <li><a href="ListQuestao.jsp">Listar</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-list"></i>
                                <span>Simulado</span>
                            </a>
                            <ul class="sub">
                                <li><a href="#">Geral</a></li>
                                <li><a href="#">Individual</a></li>
                                <li><a href="#">Listar</a></li>
                            </ul>
                        </li> 
                        <li class="sub-menu">
                            <a href="javascript:;" >
                                <i class=" fa fa-bar-chart-o"></i>
                                <span>Indicadores</span>
                            </a>
                            <ul class="sub">
                                <li><a href="#">Geral</a></li>
                                <li><a href="#">ALuno</a></li>
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
                                <h3 class="centered">
                                    <i class="fa fa-angle-double-right"></i>
                                    <b> Gerar Simulado</b>
                                </h3>
                                <br><br>
                                <%
                                    if (temp != null) {
                                        if (temp.equals("NO")) {
                                %>
                                <div class="alert alert-danger centered">
                                    <b>erro!</b> Simulado não Gerado, ou já consta em nosso sistema!
                                </div>
                                <%
                                } else {
                                %>
                                <div class="alert alert-success" centered>
                                    <b>Sucesso!</b> Simulado Gerado!
                                </div>      				
                                <%
                                        }
                                    }
                                %>
                                <form class="form-horizontal style-form" action="GerarSimulado.jsp" method="post">
                                    <%
                                        int countAssunto = 0;
                                        String tempDisciplina = "";
                                        for (int i = 0; i < list.size(); i++) {
                                            if (!tempDisciplina.equals(list.get(i).getDisciplina())) {
                                                tempDisciplina = list.get(i).getDisciplina();
                                                int count = 0;
                                                for (DisciplinaSimulado materia : list) {
                                                    if (count == 0) {
                                    %>
                                    <div class="form-group">
                                        <label class="col-sm-3 col-sm-3 control-label"><%out.print(tempDisciplina);%>:</label>
                                        <div class="col-lg-6">
                                            <%
                                                    count++;
                                                }
                                                if (materia.getDisciplina().equals(tempDisciplina)) {
                                            %>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" name="assunto<%out.print(countAssunto++);%>" value="<%out.print(materia.getId_assunto());%>"><%out.print(materia.getAssunto());%>
                                                </label>
                                            </div>
                                            <%
                                                    }
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <%
                                                count = 0;
                                            }
                                        }
                                    %>
                                    <br>
                                    <input type="hidden" class="form-control" id="user" name="user" value="<%out.print(usuario.getNome());%>">
                                    <input type="hidden" class="form-control" id="user" name="total" value="<%out.print(countAssunto);%>">
                                    <button type="submit" class="btn btn-theme">Gerar</button>
                                </form>
                            </div> 
                        </div>
                    </div>    
                    <div class="modal fade bs-example-modal-sm" id="myModalDisciplina" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Cadastro de Disciplina</h4>
                                </div>
                                <div class="modal-body">
                                    <form  class="form login" name="form1">
                                        <div class="login-wrap">
                                            Entre com o nome da disciplina:
                                            <input type="text" class="form-control" id="disciplinaModal" name="disciplinaModal" required>
                                            <hr>
                                            <input class="form-control" type="button" value="cadastrar" id="ButtonDisciplina" style="background-color: #3A5FCD; border-color: #00008B; color: #FFF"> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade bs-example-modal-sm" id="myModalAssunto" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Cadastro de Assunto</h4>
                                </div>
                                <div class="modal-body">
                                    <form  class="form login" name="form1">
                                        <div class="login-wrap">
                                            Entre com o nome do Assunto:
                                            <input type="text" class="form-control" id="assuntoModal" name="assuntoModal" required>
                                            <hr>
                                            <input class="form-control" type="button" value="cadastrar" id="ButtonAssunto" style="background-color: #3A5FCD; border-color: #00008B; color: #FFF"> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section>

            <!-- js placed at the end of the document so the pages load faster -->
            <script src="../assets3/js/jquery.js"></script>
            <script src="../assets3/js/jquery-ui-1.9.2.custom.min.js"></script>
            <script src="../assets3/js/fullcalendar/fullcalendar.min.js"></script>    
            <script src="../assets3/js/bootstrap.min.js"></script>
            <script class="include" type="text/javascript" src="../assets3/js/jquery.dcjqaccordion.2.7.js"></script>
            <script src="../assets3/js/jquery.scrollTo.min.js"></script>
            <script src="../assets3/js/jquery.nicescroll.js" type="text/javascript"></script>
            <!--common script for all pages-->
            <script src="../assets3/js/common-scripts.js"></script>
            <script>
                                $(document).ready(function (e) {
                                    var json_data1 = (function () {
                                        var json3;
                                        $.ajax({
                                            type: 'GET',
                                            url: '/sisbqs/bqs/questao/Disciplina.jsp',
                                            async: false,
                                            global: false,
                                            success: function (data) {
                                                $.each(data, function () {
                                                    $("#disciplina").append('<option value="' + this.id + '">' + this.name + '</option>')
                                                })
                                            },
                                            error: function () {
                                                alert("Ocorreu um erro ao acessar a base de dados 1");
                                            }
                                        });
                                        return json3;
                                    })();
                                    $("body").delegate("#disciplina", "change", function (data) {
                                        var teste1 = $("#disciplina").val();
                                        var json_data2 = (function () {
                                            var json3;
                                            $.ajax({
                                                type: 'GET',
                                                url: '/sisbqs/bqs/questao/Assunto.jsp',
                                                data: {dis: teste1},
                                                async: false,
                                                global: false,
                                                beforeSend: function () {
                                                    document.getElementById("assunto").innerHTML = "";
                                                    $("#assunto").append('<option value="">Selecione!</option>');
                                                },
                                                success: function (data) {
                                                    $.each(data, function () {
                                                        $("#assunto").append('<option value="' + this.id + '">' + this.name + '</option>');
                                                    });
                                                },
                                                error: function () {
                                                    alert("Ocorreu um erro ao acessar a base de dados 2");
                                                }
                                            });
                                            return json3;
                                        })();
                                    });
                                    $('#ButtonDisciplina').click(function () {
                                        var teste2 = $("#disciplinaModal").val();
                                        $.ajax({
                                            type: 'GET',
                                            url: '/sisbqs/bqs/questao/AttDisciplina.jsp',
                                            data: {dis: teste2},
                                            async: false,
                                            global: false,
                                            beforeSend: function () {
                                                document.getElementById("disciplina").innerHTML = "";
                                                $('#myModalDisciplina').modal('hide');
                                                $("#disciplina").append('<option value="">Selecione!</option>');
                                            },
                                            success: function (data) {
                                                $.each(data, function () {
                                                    $("#disciplina").append('<option value="' + this.id + '">' + this.name + '</option>');
                                                });
                                            },
                                            error: function () {
                                                alert("Ocorreu um erro ao acessar a base de dados 3");
                                            }
                                        });
                                    });
                                    $('#ButtonAssunto').click(function () {
                                        var teste3 = $("#disciplina").val();
                                        var teste4 = $("#assuntoModal").val();
                                        if (teste3 === "") {
                                            alert("Selecione primeiro disciplina antes de criar um assunto!");
                                        } else {
                                            $.ajax({
                                                type: 'GET',
                                                url: '/sisbqs/bqs/questao/AttAssunto.jsp',
                                                data: {dis: teste3, assun: teste4},
                                                async: false,
                                                global: false,
                                                beforeSend: function () {
                                                    document.getElementById("assunto").innerHTML = "";
                                                    $('#myModalAssunto').modal('hide');
                                                    $("#assunto").append('<option value="">Selecione!</option>');
                                                },
                                                success: function (data) {
                                                    $.each(data, function () {
                                                        $("#assunto").append('<option value="' + this.id + '">' + this.name + '</option>')
                                                    })
                                                },
                                                error: function () {
                                                    alert("Ocorreu um erro ao acessar a base de dados 3");
                                                }
                                            });
                                        }
                                    });
                                });
                                function perguntaImg() {
                                    $("#perg").append('<input type="file" class="" id="filePerg" name="filePerg" required>');
                                    document.getElementById('TxtQuestao').removeAttribute('required');
                                    $("#perguntaImg").html("");
                                }
                                function respA() {
                                    $("#ra").append('<input type="file" class="" id="fileRespA" name="fileRespA" required>');
                                    document.getElementById('A').removeAttribute('required');
                                    $("#respA").html("");
                                }
                                function respB() {
                                    $("#rb").append('<input type="file" class="" id="fileRespB" name="fileRespB" required>');
                                    document.getElementById('B').removeAttribute('required');
                                    $("#respB").html("");
                                }
                                function respC() {
                                    $("#rc").append('<input type="file" class="" id="fileRespC" name="fileRespC" required>');
                                    document.getElementById('C').removeAttribute('required');
                                    $("#respC").html("");
                                }
                                function respD() {
                                    $("#rd").append('<input type="file" class="" id="fileRespD" name="fileRespD" required>');
                                    document.getElementById('D').removeAttribute('required');
                                    $("#respD").html("");
                                }
                                function respE() {
                                    $("#re").append('<input type="file" class="" id="fileRespE" name="fileRespE" required>');
                                    document.getElementById('E').removeAttribute('required');
                                    $("#respE").html("");
                                }
            </script>

        </section>
    </body>
</html>
