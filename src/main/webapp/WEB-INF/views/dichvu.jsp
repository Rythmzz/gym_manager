<%@ include file="/resources/include/sidebar.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/resources/include/navbarmn.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
								<!-- <label class="tablinks btn btn-sm btn-primary btn-simple active"
									id="defaultOpen" onclick="dangky(event, 'profile')"> <input
									type="radio" name="options" checked> <span
									class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Thông
										tin</span> <span class="d-block d-sm-none"> <i
										class="tim-icons icon-single-02"></i>
								</span>
								</label> --> <label  class="tablinks btn btn-sm btn-primary btn-simple active"
									id="1" onclick="dangky(event, 'dichvu')"> <input
									type="radio" class="d-none d-sm-none" name="options"> <span
									class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Dịch
										Vụ</span> <span class="d-block d-sm-none"> <i
										class="tim-icons icon-gift-2"></i>
								</span>
								</label>

							</div>
						</div>
					</div>
				</div>
				<!-- ============================= noi dung ====================== -->
				

				<!-- =================================== end================= -->
				<div id="dichvu" class="card-body tabcontent">
					<form action="dangkydichvu" method="post">
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Mã thẻ tập</label> <input type="text"
										class="form-control" style="color: white"  readonly="true" value="${maTDV}">
								</div>
							</div>
							
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label for="exampleInputEmail1">Mã Khách Hàng</label> <input style="color: white"  readonly="true"
										class="form-control" value="${maKH}" name="maKH" >
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Lớp dịch vụ</label> 
									<select onchange="laylopdv()" id="lopdvss" style="background: #27293d" class="form-control form-select" name="lopDV">
									<option selected="selected" hidden>Chọn lớp dịch vụ</option>
										<c:forEach var="lopDV" items="${lopDVs}">
											<option>${lopDV.tenLop}</option>										
										</c:forEach>
										
									</select>
								</div>
							</div>
							<div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label>Gói tập</label> 
									<select onchange="laygoitap()" id="goitapss" style="background: #27293d" class="form-control form-select" name="goiTap">
										
										
										
									</select>
									
								</div>
							</div>
							<div class="col-md-4 pl-md-1">
								<div class="form-group">
									<label for="exampleInputEmail1">Ngày ĐK</label> <input
										type="date" value="${localDate}" style="color: white"  readonly="true" class="form-control">
								</div>
							</div>
						</div>
						<div class="row">
							<!-- <div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Ngày bắt đầu</label> <input type="date"
										class="form-control" placeholder="Ngày bắt đầu">
								</div>
							</div> -->
							<!-- <div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Ngày kết thúc</label> <input type="date"
										class="form-control" placeholder="Ngày kết thúc">
								</div>
							</div> -->
						</div>
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Giá</label> <input style="color: white" id="giatien" type="text" class="form-control"
										readonly="true" value="0.00 VNĐ">
								</div>
							</div>
						</div>
						<!-- <div class="row">
                    <div class="col-md-8">
                      <div class="form-group">
                        <label>About Me</label>
                        <textarea rows="4" cols="80" class="form-control" placeholder="Here can be your description" value="Mike">Lamborghini Mercy, Your chick she so thirsty, I'm in that two seat Lambo.</textarea>
                      </div>
                    </div>
                  </div> -->
                  <div class="card-footer">
						<button disabled id="nutluu" type="submit" class="btn btn-fill btn-primary">Lưu
							và Xuất háa đơn</button>
					</div>
					</form>
					
				</div>
				
				<script>

				const formatter = new Intl.NumberFormat('en-US', {
					  style: 'currency',
					  currency: 'VND',
					  minimumFractionDigits: 2
					})
					
										function  laygoitap(){
											//alert(document.getElementById("lopdvss").value);
											//alert(document.getElementById("goitapss").value);
											$.ajax({
							                    url : "laygiagoitap",
							                    type : "post",
							                    dataType:"text",
							                    data : {
							                         "lopDV": document.getElementById("lopdvss").value,
							                         "goiTap": document.getElementById("goitapss").value
							                    },
							                    success : function (result){
							                    	if(result.search(/<|>|html/i)==-1 && result.trim()!=""  ){
							                    		document.getElementById("giatien").value=formatter.format(result);
							                    		document.getElementById("nutluu").removeAttribute("disabled");
							                    	} else document.getElementById("nutluu").setAttribute("disabled", "");
							                        
							                    }
							                });
											}
										function  laylopdv(){
											//alert(document.getElementById("lopdvss").value);
											//alert(document.getElementById("goitapss").value);
												function layoption(start,end,ten){
													var options="<option hidden checked> </option>";
													for(var  loaigoitap = start ; loaigoitap <=end; loaigoitap++){ 
														options += "<option>"+ten[loaigoitap] +"</option>";
														
													}
													return options;
												}
												//alert(document.getElementById("lopdvss").value);
											
												$.ajax({
								                    url : "laytengoitap",
								                    type : "post",
								                    dataType:"text",
								                    data : {
								                         "lopDV": document.getElementById("lopdvss").value
								                         
								                    },
								                    success : function (result){   
								                    	//alert(result);
								                    	if(result.search(/<|>|html/i)>-1 || result.trim()==""  ){
									                    	result="";
									                    	demo.showNotification('top','right','Dịch vụ hiện tại không có gói tập!','3');
									                    	
									                    	
									                    	
								                    	}
								                    	document.getElementById("nutluu").setAttribute("disabled", "");
								                    	document.getElementById("goitapss").innerHTML=layoption(0,result.split(",").length-1,result.split(","));
								                    	document.getElementById("giatien").value=formatter.format("0");
								                    }
								                });
												
											}
										
									</script>

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
  if("${thongBao}"=="Đăng ký khách hàng thành công"){

	  demo.showNotification('top','right','Đăng ký khách hàng thành công!','2');
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