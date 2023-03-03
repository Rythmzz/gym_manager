<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
      <%@ include file="/resources/include/sidebar.jsp" %>


      <%@ include file="/resources/include/navbarmn.jsp" %>
   <c:forEach var="the_wtt" items="${thes_wtt}"> 
      <script>
      	var mathe="${the_wtt.maThe}";
			if("${the_wtt.trangThai}"=="Chưa Thanh Toán"){
				demo.showNotification('top','right',' <a style="color: white;font-size:20px" href=\"hoadon?id=${the_wtt.maThe}\">Thẻ '+mathe.trim()+' chưa được thanh toán </a> ','3');

				}



      </script>
      </c:forEach>
      
      <div class="content">
        <div class="row">
          <div class="col-12">
            <div  class="card card-chart">
              <div class="card-header ">
                <div class="row">
                  <div class="col-sm-6 text-left">
                    <h5 class="card-category">Số lượng khách hàng và</h5>
                    <h2 class="card-title">Tổng Doanh Thu</h2>
                  </div>
                  <div class="col-sm-6">
                    <div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
                      <label class="btn btn-sm btn-primary btn-simple active" id="0">
                        <input type="radio" name="options" checked>
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Doanh Thu</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-single-02"></i>
                        </span>
                      </label>
                      
                      <label class="btn btn-sm btn-primary btn-simple" id="2">
                        <input type="radio" class="d-none" name="options">
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Khách Hàng</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-tap-02"></i>
                        </span>
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <div onclick="redirect('bieudonam')" class="card-body">
                <div class="chart-area">
                  <canvas id="chartBig1"></canvas>
                </div>
              </div>
            </div>
           
          </div>
        </div>
        <div class="row">
          <div class="col-lg-6">
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
              </div>
              <div onclick="redirect('bieudodtcn')" class="card-body">
                <div class="chart-area">
                  <canvas id="chartLinePurple"></canvas>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="card card-chart">
              <div class="card-header">
                <h5 class="card-category">Dịch Vụ Truyền thống Trong Tháng</h5>
                <h3 class="card-title"><i class="tim-icons icon-bag-16 text-info"></i>${tongDV} KH</h3>
              </div>
              <div onclick="redirect('bieudodv')" class="card-body">
                <div class="chart-area">
                  <canvas id="CountryChart"></canvas>
                </div>
              </div>
            </div>
          </div>
           <script>
            function redirect(url){
            	window.location.href=url;
            }
            </script>
        </div>
        <div class="row">
          
          <div class="col-lg-12 col-md-12">
            <div class="card ">
              <div class="card-header">
                <h4 class="card-title"> Top 10 Khách Hàng Tiềm Năng</h4>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table tablesorter " id="myTable1">
                    <thead class=" text-primary">
                      <tr>
                        <th>
                          Mã KH
                        </th>
                        <th>
                          Họ Và Tên
                        </th>
                        <th>
                          Giới Tính
                        </th>
                        <th class="text-center">
                          Email
                        </th>
                        
                      
                      </tr>
                      
                     
                      
                    </thead>
                    <tbody>
                    <c:forEach var="top10KH" items="${top10KHTiemNang}">
                      <tr>
                        <td>
                          ${top10KH.maKH}
                        </td>
                        <td>
                          ${top10KH.tenKH}
                        </td>
                        <td >
                          ${top10KH.gioiTinh}
                        </td>
                        <td class="text-center" >
                          ${top10KH.email}
                        </td>
                        
                       
                        
                        
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
   
    </div>
  </div>
     <script>
function sortTable() {
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("myTable1");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.rows;
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("TD")[4];
      //alert(x.innerHTML);
      y = rows[i + 1].getElementsByTagName("TD")[4];
      //alert(parseInt(x.innerHTML.split('KH')[1]));
      //alert(parseInt(y.innerHTML.split('KH')[1]));
      //check if the two rows should switch place:
      if (parseInt(x.innerHTML) < parseInt(y.innerHTML)) {
      	//alert(parseInt(x.innerHTML.split('KH')[1]));
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      }
    }
   // break;
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
sortTable();
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
      let data_bdn_dt=${danhThuN};
      let data_bdn_kh=${bdKHN};
      let field_bdn=['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'];
		
      let data_bdn_dtcn=${doanhThuTCN};
      let field_bdn_cn=${fieldBDNCN};

      let data_bd_dv=${bdDVT}

      
      demo.initDashboardPageCharts(data_bdn_dt,data_bdn_kh,field_bdn,data_bdn_dtcn,field_bdn_cn,data_bd_dv);

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