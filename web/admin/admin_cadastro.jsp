<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="src.modelo.Usuario"%>
<%@page import="java.io.*" %>
<%Usuario usuario = (Usuario) session.getAttribute("usuario");%>
<%String temp = (String) request.getAttribute("mensagem");%>

<%@ page contentType="text/html; charset=UTF-8" %>

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
                            <div class="form-panel">
                                <h3 class="mb"><i class="fa fa-angle-double-right"></i> <b>Cadastro de Usuarios</b></h3><br><br>
                                <%if (temp != null) {
                                        if (temp.equals("OK1")) {%>
                                <div class="alert alert-success centered"><b>Concluido!</b> O Usuario foi cadastrado com sucesso.</div>
                                <%} else if (temp.equals("OK2")) {%>
                                <div class="alert alert-success centered"><b>Concluido!</b> O Usuario foi atualizado com sucesso.</div>
                                <%} else {%>
                                <div class="alert alert-danger"><b>Erro!</b> A operação não pode ser concluida.</div>      				
                                <%}
                                    }%>
                                <form class="form-horizontal style-form" action="CadastroAdmin.jsp" method="post">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Nome</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="nome" name="nome" required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">NIP</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="nip" name="nip" required="">
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Nome de Guerra</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="guerra" name="guerra"  required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-2 control-label">Posto/Graduação</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="grad">
                                                <option value="Selecione">Selecione!</option>
                                                <option value="MN">MN</option>
                                                <option value="CB">CB</option>
                                                <option value="3SG">3ºSG</option>
                                                <option value="2SG">2ºSG</option>
                                                <option value="1SG">1ºSG</option>
                                                <option value="SO">SO</option>
                                                <option value="GM">GM</option>
                                                <option value="2T">2ºT</option>
                                                <option value="1T">1ºT</option>
                                                <option value="CT">CT</option>
                                                <option value="CC">CC</option>
                                                <option value="CF">CF</option>
                                                <option value="CMG">CMG</option>
                                                <option value="CA">CA</option>
                                                <option value="VA">VA</option>
                                                <option value="AE">AE</option>
                                            </select>
                                        </div> 
                                        <label class="col-sm-1 col-sm-2 control-label">Ramal</label>
                                        <div class="col-sm-2">
                                            <input type="text" class="form-control" id="ramal" name="ramal" maxlength="4" required="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 col-sm-3 control-label">Setor</label>
                                        <div class="col-sm-2">
                                            <select class="form-control" size="1" name="setor">
                                                <option value="Selecione">Selecione!</option>
                                                <option value="BH-01">BH-01</option>
                                                <option value="BH-02">BH-02</option>
                                                <option value="BH-03">BH-03</option>
                                                <option value="BH-04">BH-04</option>
                                                <option value="BH-05">BH-05</option>
                                                <option value="BH-06">BH-06</option>
                                                <option value="BH-07">BH-07</option>
                                                <option value="BH-10">BH-10</option>
                                                <option value="BH-11">BH-11</option>
                                                <option value="BH-12">BH-12</option>
                                                <option value="BH-13">BH-13</option>
                                                <option value="BH-20">BH-20</option>
                                                <option value="BH-21">BH-21</option>
                                                <option value="BH-22">BH-22</option>
                                                <option value="BH-23">BH-23</option>
                                                <option value="BH-24">BH-24</option>
                                                <option value="BH-25">BH-25</option>
                                                <option value="BH-30">BH-30</option>
                                                <option value="BH-31">BH-31</option>
                                                <option value="BH-32">BH-32</option>
                                                <option value="BH-33">BH-33</option>
                                                <option value="BH-34">BH-34</option>
                                                <option value="BH-40">BH-40</option>
                                                <option value="BH-41">BH-41</option>
                                                <option value="BH-42">BH-42</option>
                                                <option value="BH-43">BH-43</option>
                                                <option value="BH-44">BH-44</option>
                                                <option value="BH-45">BH-45</option>
                                                <option value="BH-50">BH-50</option>
                                                <option value="BH-51">BH-51</option>
                                                <option value="BH-52">BH-52</option>
                                                <option value="BH-53">BH-53</option>
                                                <option value="BH-54">BH-54</option>
                                                <option value="BH-50">BH-55</option>

                                            </select>
                                        </div>
                                        <label class="col-sm-1 col-sm-2 control-label">Tipo de acesso</label>
                                        <div class="col-sm-3">
                                            <select class="form-control" size="1" name="type">
                                                <option value="Selecione">Selecione!</option>
                                                <option value="ADMIN">ADMIN</option>
                                                <option value="IDENTIFICADOR">Identificador</option>
                                                <option value="TOTAL">BH-01</option>
                                                <option value="TOTAL">BH-02</option>
                                                <option value="BH03">BH-03</option>
                                                <option value="BH04">BH-04</option>
                                                <option value="BH05">BH-05</option>
                                                <option value="BH06">BH-06</option>
                                                <option value="BH07">BH-07</option>
                                                <option value="BH08">BH-08</option>
                                                <option value="CHEFEBH10">BH-10 Chefe Departamento</option>
                                                <option value="NACIONAL">BH-10 Cartas Nacional</option>
                                                <option value="ESTRANGEIRA">BH-10 Cartas Estrangeira</option>
                                                <option value="FOLHAN">BH-10 Folha N</option>
                                                <option value="CHEFEBH20">BH-20 Chefe Departamento</option>
                                                <option value="MUNICIAMENTO">BH-25 Municiamento</option>
                                                <option value="CHEFEBH30">BH-30 Chefe Departamento</option>
                                                <option value="CHAPA">BH-30 CHaPA</option>
                                                <option value="ENCARREGADOBH31">BH-31 Encarregado(a)</option>
                                                <option value="PESSOAL">BH-31 Dep. Pessoal</option>
                                                <option value="ENCARREGADOBH32">BH-32 Encarregado(a)</option>
                                                <option value="ENCARREGADOBH33">BH-33 Encarregado(a)</option>
                                                <option value="ENCARREGADOBH34">BH-34 Encarregado(a)</option>
                                                <option value="SUPERVISORBH34">BH-34 Supervisor</option>
                                                <option value="HARDWARE">BH-34 Hardware</option>
                                                <option value="REDE">BH-34 Rede</option>
                                                <option value="LOTUS">BH-34 Lotus-SiGDEM</option>
                                                <option value="TELEFONIA">BH-34 Telefônia</option>
                                                <option value="CHEFEBH40">BH-40 Chefe Departamento</option>
                                                <option value="CHEFEBH50">BH-50 Chefe Departamento</option>
                                                <option value="ENCARREGADOBH52">BH-52 Encarregado(a)</option>
                                                <option value="ENCARREGADOBH53">BH-53 Encarregado(a)</option>
                                                <option value="ELETRICA">BH-50 Eletrica</option>
                                                <option value="AGUADA">BH-50 Eletrica</option>
                                                <option value="REFRIGERACAO">BH-50 Refrigeração</option>
                                                <option value="CARPINTARIA">BH-50 Carpintaria</option>
                                                <option value="METALURGICA">BH-50 Metalurgia</option>
                                                <option value="PREFEITURA">BH-50 Prefeitura</option>
                                                <option value="GARAGEM">BH-50 Garagem</option>
                                            </select>
                                        </div> 
                                    </div>
                                    <br>
                                    <hr>
                                    <br>
                                    <div>
                                        <button type="submit" class="btn btn-theme">Cadastrar</button>
                                        <input type="button"  class="btn btn-theme"  value="Cancelar" onClick="Voltar()">
                                    </div>
                                    <br>
                                    <br>
                                </form>
                            </div>
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
