<%@ page contentType="text/html" pageEncoding="UTF-8" %>
      <%@ include file="/resources/include/sidebar.jsp" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
      <%@ include file="/resources/include/navbarmn.jsp" %>
   
      
      <div class="content">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card card-chart">
              <div class="card-header">
                <h5 class="card-category">Doanh Thu Cao Nhất</h5>
                <h3 class="card-title"><i class="tim-icons icon-money-coins text-primary"></i><span id="tienDTCN"></span></h3>
                <script>
				const formatter = new Intl.NumberFormat('en-US', {
				  style: 'currency',
				  currency: 'VND',
				  minimumFractionDigits: 2
				})
				document.getElementById("tienDTCN").innerHTML= formatter.format(${maxDT});
				</script>
                <div class="row">
                    <div class="col-md-2 pr-md-1">
                      <div class="form-group">
                        <label>Năm</label>
                        <form id="bieudonam_tk" action="bieudodtcn" method="post">
                        <select  style="background: #27293d" class="form-control form-select" name="nam" id="years">
						    <option id="nam_mac_dinh" selected="" hidden="" ></option>
						  </select>
                        </form>
                      </div>
                    </div>
                    
                   
                     <div class="col-md-3 pl-md-1">
	                	<div  style="margin-top: 19px">
	                	<button onclick="document.getElementById('bieudonam_tk').submit();"  type="submit" class="btn btn-fill btn-primary">Tìm kiếm</button>
	              		</div>
	              	</div>
                  </div>
              </div>
              <div class="card-body">
                <div style="height: 430px" class="chart-area">
                  <canvas id="chartLinePurple"></canvas>
                </div>
              </div>
            </div>
          </div>
         
          
        </div>
        
      </div>
      <script>
      var start = 2008;
		var end = new Date().getFullYear();
		var options = "";
		for(var year = start ; year <=end; year++){
		  
		  options += "<option>"+ year +"</option>";
		}
		document.getElementById("years").innerHTML += options;
		if("${namHienTai}"!=""){
			document.getElementById("nam_mac_dinh").innerHTML = "${namHienTai}";
			}else document.getElementById("nam_mac_dinh").innerHTML = end;
		
		</script>					
   
  <div class="fixed-plugin">
    <div class="dropdown show-dropdown">
      <a href="#" data-toggle="dropdown">
        <i class="fa fa-cog fa-2x"> </i>
      </a>
      <ul class="dropdown-menu">
        <li class="header-title"> Sidebar Background</li>
        <li class="adjustments-line">
          <a href="javascript:void(0)" class="switch-trigger background-color">
            <div class="badge-colors text-center">
              <span class="badge filter badge-primary active" data-color="primary"></span>
              <span class="badge filter badge-info" data-color="blue"></span>
              <span class="badge filter badge-success" data-color="green"></span>
            </div>
            <div class="clearfix"></div>
          </a>
        </li>
        <li class="adjustments-line text-center color-change">
          <span class="color-label">LIGHT MODE</span>
          <span class="badge light-badge mr-2"></span>
          <span class="badge dark-badge ml-2"></span>
          <span class="color-label">DARK MODE</span>
        </li>
        <li class="button-container">
          <a href="https://www.creative-tim.com/product/black-dashboard" target="_blank" class="btn btn-primary btn-block btn-round">Download Now</a>
          <a href="https://demos.creative-tim.com/black-dashboard/docs/1.0/getting-started/introduction.html" target="_blank" class="btn btn-default btn-block btn-round">
            Documentation
          </a>
        </li>
        <li class="header-title">Thank you for 95 shares!</li>
        <li class="button-container text-center">
          <button id="twitter" class="btn btn-round btn-info"><i class="fab fa-twitter"></i> &middot; 45</button>
          <button id="facebook" class="btn btn-round btn-info"><i class="fab fa-facebook-f"></i> &middot; 50</button>
          <br>
          <br>
          <a class="github-button" href="https://github.com/creativetimofficial/black-dashboard" data-icon="octicon-star" data-size="large" data-show-count="true" aria-label="Star ntkme/github-buttons on GitHub">Star</a>
        </li>
      </ul>
    </div>
  </div>

  <script>
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');
        $navbar = $('.navbar');
        $main_panel = $('.main-panel');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');
        sidebar_mini_active = true;
        white_color = false;

        window_width = $(window).width();

        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



        $('.fixed-plugin a').click(function(event) {
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .background-color span').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data', new_color);
          }

          if ($main_panel.length != 0) {
            $main_panel.attr('data', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data', new_color);
          }
        });

        $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            sidebar_mini_active = false;
            blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
          } else {
            $('body').addClass('sidebar-mini');
            sidebar_mini_active = true;
            blackDashboard.showSidebarMessage('Sidebar mini activated...');
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);
        });

        $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (white_color == true) {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').removeClass('white-content');
            }, 900);
            white_color = false;
          } else {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').addClass('white-content');
            }, 900);

            white_color = true;
          }


        });

        $('.light-badge').click(function() {
          $('body').addClass('white-content');
        });

        $('.dark-badge').click(function() {
          $('body').removeClass('white-content');
        });
      });
    });
  </script>
  <script>
    $(document).ready(function() {
      // Javascript method's body can be found in assets/js/demos.js
    	let data_bdn_dtcn=${doanhThuTCN};
        let field_bdn_cn=${fieldBDNCN};
        demo.initDashboardPageCharts(0,0,0,data_bdn_dtcn,field_bdn_cn,0);

    });
  </script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "black-dashboard-free"
      });
  </script>
</body>

</html>