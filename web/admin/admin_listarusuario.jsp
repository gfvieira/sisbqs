<%@page import="src.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%ArrayList<Usuario> list = (ArrayList<Usuario>) request.getAttribute("lista");%>
<%int cont = (Integer) request.getAttribute("cont");%>
<%Usuario usuario = (Usuario) session.getAttribute("usuario");%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">

        <title>SIG-BASE || WEB-MASTER</title>

        <!-- Bootstrap core CSS -->
        <link href="assets2/css/bootstrap.css" rel="stylesheet">
        <!--external css-->
        <link href="assets2/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="assets2/css/zabuto_calendar.css">
        <link rel="stylesheet" type="text/css" href="assets2/js/gritter/css/jquery.gritter.css" />
        <link rel="stylesheet" type="text/css" href="assets2/lineicons/style.css">    

        <!-- Custom styles for this template -->
        <link href="assets2/css/style.css" rel="stylesheet">
        <link href="assets2/css/style-responsive.css" rel="stylesheet">

        <script src="assets2/js/chart-master/Chart.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

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
                <a href="index.html" class="logo"><b>SIG-BASE || WEB-MASTER</b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li><a class="logout" href="/sigbase/LogOff.jsp">Sair</a></li>
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
                    <!-- sidebar menu start-->
                    <ul class="sidebar-menu" id="nav-accordion">

                        <p class="centered"><a href="profile.html"><img src="assets2/img/CBV.png" class="img-circle" width="80"></a></p>
                        <h5 class="centered">BHMN</h5>

                        <li class="mt">
                            <a class="active" href="admin_home.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Principal</span>
                            </a>
                        </li>
                        <li class="mt">
                            <a class="active" href="admin_cadastro.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Cadastrar</span>
                            </a>
                        </li>
                        <li class="mt">
                            <a class="active" href="ListarUsuariosAdmin.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>Listar</span>
                            </a>
                        </li>
                        <li class="mt">
                            <a class="active" href="/SIG-BASE/LogOff.jsp">
                                <i class="fa fa-dashboard"></i>
                                <span>LogOff</span>
                            </a>
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
                    <div class="row mt">
                        <div class="col-lg-12">
                            <br> <br>
                            <h2 class="thin text-center"><%out.print(cont);%> Usuarios Encontrados:</h2>
                            <table class="table table-striped table-advance table-hover">
                                <hr>
                                <thead>
                                    <tr>
                                        <th><i class="fa fa-angle-double-right"></i> Nip</th>
                                        <th><i class="fa fa-angle-double-right"></i> Usuario</th>                                 
                                        <th><i class="fa fa-angle-double-right"></i> Setor</th>
                                        <th><i class="fa fa-angle-double-right"></i> Ramal</th>
                                        <th><i class="fa fa-angle-double-right"></i> Privilegio</th>
                                        <th><i class="fa fa-angle-double-right"></i> Tipo de acesso</th>
                                        <th><i class="fa fa-angle-double-right"></i> Status</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Usuario user : list) {%>
                                    <tr>
                                        <td><%out.print(user.getNip());%></td>
                                        <td><%out.print(user.getPost());%>-<%out.print(user.getGuerra());%></td>
                                        <td><%out.print(user.getSetor());%></td>
                                        <td><%out.print(user.getRamal());%></td>
                                        <td><%out.print(user.getPri());%></td>
                                        <td><%out.print(user.getTypeAccess());%></td>
                                        <%if (user.getAtivo() == 1 && user.getNip().equals("12345678")) {%>
                                        <td><span class="label label-warning label-mini">TESTE</span></td>
                                        <%} else if (user.getAtivo() == 1) {%>
                                        <td><span class="label label-success label-mini">ATIVO</span></td>
                                        <%} else {%>
                                        <td><span class="label label-danger label-mini">DESATIVADO</span></td>
                                        <%}%>
                                        <td>
                                            <form class="col-sm-2 control-label" action="ExibirUsuarioAdmin.jsp" method="post">      
                                                <button type="submit" title="EXIBIR USUARIO" class="btn btn-success btn-xs"><i class="fa fa-check"></i></button>
                                                <input type="hidden" class="form-control" id="nip" name="nip" value="<% out.print(user.getNip()); %>" > 
                                            </form>
                                        </td>
                                    </tr>
                                    <% }%>


                                </tbody>
                            </table>
                        </div>
                    </div><!--/row -->
                    <br><br><br><br><br>
                </section>
            </section>

            <!--main content end-->
            <!--footer start-->
            <footer class="site-footer">
                <div class="text-center">
                    2015 - BHMN
                    <a href="index.html#" class="go-top">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div>
            </footer>
            <!--footer end-->
        </section>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="assets2/js/jquery.js"></script>
        <script src="assets2/js/jquery-1.8.3.min.js"></script>
        <script src="assets2/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="assets2/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="assets2/js/jquery.scrollTo.min.js"></script>
        <script src="assets2/js/jquery.nicescroll.js" type="text/javascript"></script>
        <script src="assets2/js/jquery.sparkline.js"></script>


        <!--common script for all pages-->
        <script src="assets2/js/common-scripts.js"></script>

        <script type="text/javascript" src="assets2/js/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript" src="assets2/js/gritter-conf.js"></script>

        <!--script for this page-->
        <script src="assets2/js/sparkline-chart.js"></script>    
        <script src="assets2/js/zabuto_calendar.js"></script>	



        <script type="application/javascript">
            $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
            $(this).hide();
            });

            $("#my-calendar").zabuto_calendar({
            action: function () {
            return myDateFunction(this.id, false);
            },
            action_nav: function () {
            return myNavFunction(this.id);
            },
            ajax: {
            url: "show_data.php?action=1",
            modal: true
            },
            legend: [
            {type: "text", label: "Special event", badge: "00"},
            {type: "block", label: "Regular event", }
            ]
            });
            });


            function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
            }
        </script>


    </body>
</html>
