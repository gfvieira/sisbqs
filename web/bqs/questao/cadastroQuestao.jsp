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
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String temp = (String) request.getAttribute("mensagem");
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
                                <h3 class="centered"><i class="fa fa-angle-double-right"></i><b> Cadastro de Questão</b></h3><br><br>
                                        <%
                                            if (temp != null) {
                                                if (temp.equals("NO")) {
                                        %>
                                <div class="alert alert-danger centered"><b>erro!</b> Questão não cadastrada, ou já consta em nosso sistema!</div>
                                <%
                                } else {
                                %>
                                <div class="alert alert-success" centered><b>Sucesso!</b> Questão Cadastrada!</div>      				
                                <%
                                        }
                                    }
                                %>
                                <form class="form-horizontal style-form" action="CadQuestao.jsp" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Disciplina:</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" size="1" name="disciplina" id="disciplina" required>
                                                <option value="">Selecione!</option>
                                            </select>
                                            <a href="javascript:void(0)" id="teste" data-toggle="modal" data-target="#myModalDisciplina" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Cadastrar nova disciplina</a>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Assunto:</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" size="1" name="assunto" id="assunto" required>
                                                <option value="">Selecione!</option>
                                            </select>
                                            <a href="javascript:void(0)" id="teste" data-toggle="modal" data-target="#myModalAssunto" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Cadastrar novo assunto</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Origem:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="origem" name="origem" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Questão:</label>
                                        <div class="col-sm-9" id="perg">
                                            <textarea class="form-control" id="TxtQuestao" rows="3" name="TxtQuestao" maxlength="5000" placeholder="Digite aqui a questão" required></textarea>
                                            <a href="javascript:void(0)" id="perguntaImg" onclick="perguntaImg();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">A-</label>
                                        <div class="col-sm-9" id="ra">
                                            <input type="text" class="form-control" id="A" name="A" required>
                                            <a href="javascript:void(0)" id="respA" onclick="respA();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">B-</label>
                                        <div class="col-sm-9" id="rb">
                                            <input type="text" class="form-control" id="B" name="B" required>
                                            <a href="javascript:void(0)" id="respB" onclick="respB();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">C-</label>
                                        <div class="col-sm-9" id="rc">
                                            <input type="text" class="form-control" id="C" name="C" required>
                                            <a href="javascript:void(0)" id="respC" onclick="respC();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">D-</label>
                                        <div class="col-sm-9" id="rd">
                                            <input type="text" class="form-control" id="D" name="D" required>
                                            <a href="javascript:void(0)" id="respD" onclick="respD();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">E-</label>
                                        <div class="col-sm-9" id="re">
                                            <input type="text" class="form-control" id="E" name="E" required>
                                            <a href="javascript:void(0)" id="respE" onclick="respE();" style="font-size: 12px; color: red;"><i class="fa fa-plus" style="font-size: 12px; color: black"></i> Adicionar figura</a>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Resposta 1:</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="resposta1" required>
                                                <option value="">Selecione!</option>
                                                <option value="A">A</option>
                                                <option value="B">B</option>
                                                <option value="C">C</option>
                                                <option value="D">D</option>
                                                <option value="E">E</option>
                                            </select>
                                        </div>
                                        <label class="col-sm-2 col-sm-2 control-label">Resposta 2:</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="resposta2">
                                                <option value="">Selecione!</option>
                                                <option value="A">A</option>
                                                <option value="B">B</option>
                                                <option value="C">C</option>
                                                <option value="D">D</option>
                                                <option value="E">E</option>
                                            </select>
                                        </div>
                                    </div>
                                    <input type="hidden" class="form-control" id="user" name="user" value="<%out.print(usuario.getNome());%>">
                                    <button type="submit" class="btn btn-theme">Cadastrar</button>
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
