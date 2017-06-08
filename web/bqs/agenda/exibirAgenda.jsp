<%@page import="src.modelo.AlunoAuditoria"%>
<%@page import="src.modelo.Aluno"%>
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
    Aluno aluno = (Aluno) request.getAttribute("aluno");
    ArrayList <AlunoAuditoria> auditoria = (ArrayList <AlunoAuditoria>) request.getAttribute("auditoria");%>
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
                                <li><a href="#">Criar</a></li>
                                <li><a href="#">Disciplina</a></li>
                                <li><a href="#">Listar</a></li>
                                <li><a href="#">Todas</a></li>
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
                                <h3 class="centered"><i class="fa fa-angle-double-right"></i><b> Dados do Aluno</b></h3><br><br>
                                <form class="form-horizontal style-form" action="AttAluno.jsp" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Matricula:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" value="<%out.print(aluno.getMatricula());%>" disabled>
                                            <input type="hidden" class="form-control" id="matricula" name="matricula" value="<%out.print(aluno.getMatricula());%>">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Nome:</label>
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control" id="nome" name="nome" value="<%out.print(aluno.getNome());%>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">CPF:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="cpf" name="cpf" value="<%out.print(aluno.getCpf());%>" disabled>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">RG:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="rg" name="rg" value="<%out.print(aluno.getRg());%>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">E-mail:</label>
                                        <div class="col-sm-7">
                                            <input type="text" class="form-control" id="mail" name="mail" value="<%out.print(aluno.getMail());%>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Telefone:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="tel" name="tel" value="<%out.print(aluno.getTel());%>" disabled>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Celular:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="cel" name="cel" value="<%out.print(aluno.getCel());%>" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-2">
                                            <input type="hidden" class="form-control" id="user" name="user" value="<%out.print(usuario.getNip());%>">
                                            <input class="form-control" id="atualizar" disabled name="atualizar" value="Atualizar" type="submit" style="background-color: #3A5FCD; border-color: #00008B; color: #FFF"> 
                                        </div>                                        
                                        <div class="col-sm-2">
                                            <a href="javascript:void(0)" id="teste" onclick="teste();" style="font-size: 12px; color: red;">Clique aqui para Editar</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>    
                                            
                    <div class="col-md-12 mt">
                        <div class="content-panel">
                            <table class="table table-hover">
                                <h2 class="centered">Histórico do Aluno:</h2>
                                <hr>
                                <thead>
                                    <tr>
                                        <th><i class="fa fa-angle-double-right text-center"></i> Data/Hota</th>
                                        <th><i class="fa fa-angle-double-right text-center"></i> Usuario</th>
                                        <th><i class="fa fa-angle-double-right text-center"></i> Descrição</th>
                                        <th><i class="fa fa-angle-double-right text-center"></i> Ip</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (AlunoAuditoria audi : auditoria) {%>
                                    <tr>
                                <td><i class="center-block"></i><%out.print(audi.getDataalt());%> às <%out.print(audi.getHoraalt());%></td>
                                <td><i class="center-block"></i><%out.print(audi.getUseralt());%></td>
                                <td><i class="center-block"></i><%out.print(audi.getDescAuditoria());%></td>
                                <td><i class="center-block"></i><%out.print(audi.getIpalt());%></td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div><!--/row -->
                    </div><!--/row --> 
                    </div>

                    <!-- page end-->
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
                                                function teste() {
                                                    document.getElementById('atualizar').removeAttribute('disabled');
                                                    document.getElementById('nome').removeAttribute('disabled');
                                                    document.getElementById('cpf').removeAttribute('disabled');
                                                    document.getElementById('rg').removeAttribute('disabled');
                                                    document.getElementById('mail').removeAttribute('disabled');
                                                    document.getElementById('cel').removeAttribute('disabled');
                                                    document.getElementById('tel').removeAttribute('disabled');
                                                    $("#teste").html("");
                                                }
            </script>
        </section>
    </body>
</html>
