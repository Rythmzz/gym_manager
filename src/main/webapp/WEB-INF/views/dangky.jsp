
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/resources/include/sidebar.jsp" %>
  <%@ page contentType="text/html" pageEncoding="UTF-8" %>    
      <%@ include file="/resources/include/navbarmn.jsp" %>
<link href="../resources/assets/css/black-dashboard.css?v=1.0.0">
<div class="content">
	<div class="row">
		<div class="col-md-10">
			<div class="card">
				<div class="card-header ">
					<div class="row">
						<div class="col-sm-6 text-left">
							<h3 class="card-title">ĐĂNG KÝ</h3>
						</div>
						<div class="col-sm-6">
							<div class="btn-group btn-group-toggle float-right"
								data-toggle="buttons">
								<label class="tablinks btn btn-sm btn-primary btn-simple active"
									id="defaultOpen" onclick="dangky(event, 'profile')"> <input
									type="radio" name="options" checked> <span
									class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Thông
										tin</span> <span class="d-block d-sm-none"> <i
										class="tim-icons icon-single-02"></i>
								</span>
								</label> <!-- <label class="tablinks btn btn-sm btn-primary btn-simple"
									id="1" onclick="dangky(event, 'dichvu')"> <input
									type="radio" class="d-none d-sm-none" name="options"> <span
									class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Dịch
										Vụ</span> <span class="d-block d-sm-none"> <i
										class="tim-icons icon-gift-2"></i>
								</span> -->
								</label>

							</div>
						</div>
					</div>
				</div>
				<!-- ============================= noi dung ====================== -->
				<div id="profile" class="card-body tabcontent">
					<form action="dangkyprofile" enctype="multipart/form-data" method="POST">
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Họ Và Tên</label> <input type="text"
										class="form-control" placeholder="Họ và tên" value="${hoVaTen}" name = "hovaten">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Email</label> <input type="email" class="form-control"
										placeholder="mike@email.com" name = "email">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Số Điện Thoại</label> 
									
									<input type="text" class="form-control" placeholder="SĐT" value="${sdt}" name = "sdt" pattern="(\+84|0)\d{9,10}"
									title="Số điện thoại gồm 10 chữ số (nếu nhập 0) hoặc 9 số (nếu nhập +84)">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Ngày Sinh</label> <input type="date"
										class="form-control" placeholder="Ngày sinh" value="${ngaySinh}" id="userdate" onchange="TDate()" name = "ngaysinh">
								</div>
							</div>
						</div>
						<script>
									function TDate() {
										
									    var UserDate = document.getElementById("userdate").value;
									    var ToDate = new Date();

									    if (new Date(UserDate).getTime() > ToDate.getTime()) {
									    	demo.showNotification('top','right','Ngày sinh không lớn hơn ngày hiện tại!','4');
									          document.getElementById("userdate").value="";
									     }
									    return true;
									}</script>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>Địa Chỉ</label> <input type="text" class="form-control"
										placeholder="Địa chỉ" value="${diaChi}" name = "diachi">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-5 pr-md-1">
								<div class="form-group">
									<label>Giới tính</label> 
									<select class="form-control form-select" name = "gioitinh">
										<option selected="selected" hidden style="background: #27293d" class="form-group">${gioiTinh}</option>
										<option style="background: #27293d" class="form-group">Nam</option>
										<option style="background: #27293d" class="form-group">Nữ</option>
									</select>
								</div>
							</div>
							<div >
								<div >
									<label>Ảnh</label> <input type="file" class="form-control" placeholder="Link ảnh" name="avatar">
									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-8">
								<div class="form-group">
									<label>Ghi chú</label>
									<textarea rows="4" cols="80" class="form-control"
										placeholder="Ghi chú thông tin khách hàng" value="Mike"></textarea>
								</div>
							</div>
						</div>
						<div class="card-footer">
						<button type="submit" class="btn btn-fill btn-primary">Lưu</button>
					</div>
					</form>
					
				</div>

				<!-- =================================== end================= -->
				<!-- <div id="dichvu" class="card-body tabcontent">
					<form>
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Mã thẻ tập</label> <input type="text"
										class="form-control" disabled="" value="123456">
								</div>
							</div>
							
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label for="exampleInputEmail1">ID Khách Hàng</label> <input
										class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Lớp dịch vụ</label> 
									<select style="background: #27293d" class="form-control form-select">
										<option  >Gym</option>
										<option >Yoga</option>
										<option >Boxing</option>
										<option >Erobic</option>
									</select>
								</div>
							</div>
							<div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label>Gói tập</label> 
									<select style="background: #27293d" class="form-control form-select">
										<option>Gói 1 ngày</option>
										<option>Gói 7 ngày</option>
										<option>Gói 1 tháng</option>
										<option>Gói 3 tháng</option>
									</select>
								</div>
							</div>
							<div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label for="exampleInputEmail1">Ngày ĐK</label> <input
										type="date" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Ngày bắt đầu</label> <input type="date"
										class="form-control" placeholder="Ngày bắt đầu">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Ngày kết thúc</label> <input type="date"
										class="form-control" placeholder="Ngày kết thúc">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Giá</label> <input type="text" class="form-control"
										disabled="" value="1,000,000 VNĐ">
								</div>
							</div>
						</div>
						<div class="row">
                    <div class="col-md-8">
                      <div class="form-group">
                        <label>About Me</label>
                        <textarea rows="4" cols="80" class="form-control" placeholder="Here can be your description" value="Mike">Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.</textarea>
                      </div>
                    </div>
                  </div>
					</form>
					<div class="card-footer">
						<button type="submit" class="btn btn-fill btn-primary">Lưu
							và Xuất HĐ</button>
					</div>
				</div> -->

				<!-- =========================================== end2 =========== -->


				<!--========================scirpt start 1 trong 2 mode -->

				<script>
				function dangky(evt, cityName) {
				  var i, tabcontent, tablinks;
				  tabcontent = document.getElementsByClassName("tabcontent");
				  for (i = 0; i < tabcontent.length; i++) {
				    tabcontent[i].style.display = "none";
				  }
				  tablinks = document.getElementsByClassName("tablinks");
				  for (i = 0; i < tablinks.length; i++) {
				    tablinks[i].className = tablinks[i].className.replace(" active", "");
				  }
				  document.getElementById(cityName).style.display = "block";
				  evt.currentTarget.className += " active";
				}
				
				// Get the element with id="defaultOpen" and click on it
				document.getElementById("defaultOpen").click();
				</script>

				<!--======================= end script ======= -->

			</div>
		</div>
	
	</div>
</div>

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
<!-- Black Dashboard DEMO methods, don't include it in your project! -->
<script src="../resources/assets/demo/demo.js"></script>

<script> 
	if("${thongBaoLoi}" == "Email đã tồn tại"){
		demo.showNotification('top','right','Email đã tồn tại!','3');

	}

</script>


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