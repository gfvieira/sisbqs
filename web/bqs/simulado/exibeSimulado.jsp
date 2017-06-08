<%@page import="src.modelo.Questao"%>
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
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    ArrayList<Questao> list = (ArrayList<Questao>) request.getAttribute("simulado");
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
                                <h3 class="centered"><b> SIMULADO</b></h3><br><br>
                                <%
                                    if (temp != null) {
                                        if (temp.equals("NO")) {
                                %>
                                <div class="alert alert-danger centered"><b><%out.print(temp);%></b></div>     				
                                        <%
                                                }
                                            }
                                        %>
                                <form class="form-horizontal style-form" action="CadSimulado.jsp" method="post">
                                    <hr>
                                    <%
                                        int count = 0;
                                        for (Questao questao : list) {
                                    %>
                                    <div class="form-group">
                                        <div class="col-sm-5" style="margin-top: 30px;">
                                            Disciplina: <%out.print(questao.getNomeDisciplina());%>
                                        </div>
                                        <div class="col-sm-5" style="margin-top: 30px;">
                                            Assunto: <%out.print(questao.getNomeAssunto());%>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10" style="padding-bottom: 10px; margin-bottom: 10px;">
                                            <%out.print(++count);%>- (<%out.print(questao.getOrigem());%>/Cod.: <%out.print(questao.getCodigo());%>) <%out.print(questao.getPergunta());%>
                                            <input type="hidden" class="form-control" name="pergunta<%out.print(count);%>" value="<%out.print(questao.getCodigo());%>">
                                        </div>
                                    </div>
                                    <div class="form-group" style="padding-bottom: 10px; margin-bottom: 10px;">
                                        <div class="col-sm-10">
                                            <%out.print(questao.getA());%> 
                                        </div>
                                    </div>
                                    <div class="form-group" style="padding-bottom: 10px; margin-bottom: 10px;">
                                        <div class="col-sm-10">
                                            <%out.print(questao.getC());%>
                                        </div>                                    
                                    </div>
                                    <div class="form-group" style="padding-bottom: 10px; margin-bottom: 10px;">
                                        <div class="col-sm-10">
                                            <%out.print(questao.getC());%>
                                        </div>                                    
                                    </div>
                                    <div class="form-group" style="padding-bottom: 10px; margin-bottom: 10px;">
                                        <div class="col-sm-10">
                                            <%out.print(questao.getD());%>
                                        </div>                                    
                                    </div>
                                    <div class="form-group" style="padding-bottom: 10px; margin-bottom: 10px;">
                                        <div class="col-sm-10">
                                            <%out.print(questao.getE());%>
                                        </div>                                    
                                    </div>
                                    <%
                                        }
                                    %>
                                    <input type="hidden" class="form-control" id="user" name="user" value="<%out.print(usuario.getNome());%>">
                                    <button type="submit" class="btn btn-theme">Gravar e Imprimir</button>
                                </form>
                            </div>
                        </div>    
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
        </section>
    </body>
</html>
