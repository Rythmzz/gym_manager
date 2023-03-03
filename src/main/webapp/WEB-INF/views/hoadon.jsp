<%@ include file="/resources/include/sidebar.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/navbarmn.jsp"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!--   Core JS Files   -->
<script src="../resources/assets/js/core/jquery.min.js"></script>
<script src="../resources/assets/js/core/popper.min.js"></script>
<script src="../resources/assets/js/core/bootstrap.min.js"></script>
<script
	src="../resources/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
<!--  Google Maps Plugin    -->
<!-- Place this tag in your head or just before your close body tag. -->
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!-- Chart JS -->
<script src="../resources/assets/js/plugins/chartjs.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../resources/assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
<script src="../resources/assets/js/black-dashboard.min.js?v=1.0.0"></script>
<script src="../resources/assets/demo/demo.js"></script>
<c:forEach var="the" items="${mathe}">

<div class="content">
	<div class="row">
		<div class="col-md-10">
			<div class="card">
				<div class="card-header">
					<h3 class="title">HÓA ĐƠN</h3>
				</div>
				<div class="card-body">
					<form action="hoadon" method="post">
						
						<div class="row">
							<div class="col-md-3 pr-md-1">
								<div class="form-group">
									<label>Mã thẻ</label> <input name="id" type="text"
										class="form-control" style="color: #FFFFFFCC"  placeholder="Company"
										value="${the.maThe}" >
								</div>
							</div>
							<div class="col-md-5 px-md-1">
								<div class="form-group">
									<label>Tên KH</label> <input type="text" class="form-control"
										placeholder="Username" value="${the.khachHang.tenKH}">
								</div>
							</div>
							<div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label for="exampleInputEmail1">Ngày ĐK</label> <input
										type="date" value="${the.ngayDK}" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Lớp DV</label> 
									<input  type="text"
										class="form-control" style="color: #FFFFFFCC" disabled="" 
										value="${the.goiTap.lopDV.tenLop}">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Gói tập</label> 
									<input  type="text" class="form-control" style="color: #FFFFFFCC" disabled="" value="${the.goiTap.tenGoiTap}">
								</div>
							</div>
						</div>
					
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Địa chỉ</label> <input type="text" class="form-control"
										placeholder="Home Address"
										value="${the.khachHang.diaChi}">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>SĐT</label> <input type="text" class="form-control"
										placeholder="SĐT" value="${the.khachHang.sdt}">
								</div>
							</div>
							<div class="col-md-4 px-md-1">
								<div class="form-group">
									<label>Email</label> <input type="email" class="form-control"
										placeholder="Email" value="${the.khachHang.email}">
								</div>
							</div>
							<!-- <div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label>Tổng tiền</label> <input type="number"
										class="form-control" placeholder="1,000,000 VNĐ">
								</div>
							</div> -->
						</div>
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Tổng tiền</label>
									<h3>${the.goiTap.gia} VNĐ</h3>
								</div>
							</div>
						</div>
							<div class="card-footer">
								<button  id="button"  type="submit" class="btn btn-fill btn-primary">Thanh
									toán</button>
							</div>
							
						</form>
						
						<script >
						
							if ("Hoạt Động"=="${the.trangThai}"||"Hết Hạn"=="${the.trangThai}"){
								document.getElementById("button").innerHTML ="Đã thanh toán";
								document.getElementById("button").setAttribute("disabled", " ");
								if("1"=="${updatetrth}" ){
									demo.showNotification('top','right','Thanh Toán Thành Công','2');	
									
								}
							}
						
							/* document.getElementById("thongbao").removeAttribute("disabled");
							document.getElementById("thongbao").click(); */

						</script>
											
							
				</div>
				
			</div>
		</div>

	</div>
</div>
</c:forEach>

</div>
</div>
<div class="fixed-plugin">
	<div class="dropdown show-dropdown">
		<a href="#" data-toggle="dropdown"> <i class="fa fa-cog fa-2x">
		</i>
		</a>
		<ul class="dropdown-menu">
			<li class="header-title">Sidebar Background</li>
			<li class="adjustments-line"><a href="javascript:void(0)"
				class="switch-trigger background-color">
					<div class="badge-colors text-center">
						<span class="badge filter badge-primary active"
							data-color="primary"></span> <span
							class="badge filter badge-info" data-color="blue"></span> <span
							class="badge filter badge-success" data-color="green"></span>
					</div>
					<div class="clearfix"></div>
			</a></li>
			<li class="adjustments-line text-center color-change"><span
				class="color-label">LIGHT MODE</span> <span
				class="badge light-badge mr-2"></span> <span
				class="badge dark-badge ml-2"></span> <span class="color-label">DARK
					MODE</span></li>
			<li class="button-container"><a
				href="https://www.creative-tim.com/product/black-dashboard"
				target="_blank" class="btn btn-primary btn-block btn-round">Download
					Now</a> <a
				href="https://demos.creative-tim.com/black-dashboard/docs/1.0/getting-started/introduction.html"
				target="_blank" class="btn btn-default btn-block btn-round">
					Documentation </a></li>
			<li class="header-title">Thank you for 95 shares!</li>
			<li class="button-container text-center">
				<button id="twitter" class="btn btn-round btn-info">
					<i class="fab fa-twitter"></i> &middot; 45
				</button>
				<button id="facebook" class="btn btn-round btn-info">
					<i class="fab fa-facebook-f"></i> &middot; 50
				</button> <br> <br> <a class="github-button"
				href="https://github.com/creativetimofficial/black-dashboard"
				data-icon="octicon-star" data-size="large" data-show-count="true"
				aria-label="Star ntkme/github-buttons on GitHub">Star</a>
			</li>
		</ul>
	</div>
</div>

<!-- Black Dashboard DEMO methods, don't include it in your project! -->

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