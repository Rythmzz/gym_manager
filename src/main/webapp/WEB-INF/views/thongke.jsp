<%@ include file="/resources/include/sidebar.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!- -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--   Core JS Files   -->
  <script src="../resources/assets/js/core/jquery.min.js"></script>
  <script src="../resources/assets/js/core/popper.min.js"></script>
  <script src="../resources/assets/js/core/bootstrap.min.js"></script>
  <script src="../resources/assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Google Maps Plugin    -->
  <!-- Place this tag in your head or just before your close body tag. -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <!-- Chart JS -->
  <script src="../resources/assets/js/plugins/chartjs.min.js"></script>
  <!--  Notifications Plugin    -->
  <script src="../resources/assets/js/plugins/bootstrap-notify.js"></script>
  <!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="../resources/assets/js/black-dashboard.min.js?v=1.0.0"></script><!-- Black Dashboard DEMO methods, don't include it in your project! -->
  <script src="../resources/assets/demo/demo.js"></script>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
$(document).ready(function(){
	  $("#myInput1").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable1 tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
$(document).ready(function(){
	  $("#myInput2").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable2 tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>

  <%@ page contentType="text/html" pageEncoding="UTF-8" %>    
      <%@ include file="/resources/include/navbarmn.jsp" %>
      <div class="content">
        <div class="row">
          <div class="col-md-8">
            <div class="card">
              <div class="card-header ">
                <div class="row">
                  <div class="col-sm-6 text-left">
                    <h5 class="card-category">Bảng</h5>
                    <h2 class="card-title">Thống Kê Trong 1 Tháng</h2>
                  </div>
                  <div class="col-sm-6">
                    <div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
                      <label name="flagDT"  class="tablinks btn btn-sm btn-primary btn-simple flagDT  " id="1" onclick="dangky(event, 'doanhthu')">
                        <input type="radio" name="options" checked>
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Doanh Thu</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-single-02"></i>
                        </span>
                      </label>
                      <label name="flagDV" class="tablinks btn btn-sm btn-primary btn-simple "   onclick="dangky(event, 'dichvu')">
                        <input type="radio" class="d-none d-sm-none" name="options">
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Dịch Vụ</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-gift-2"></i>
                        </span>
                      </label>
                      
                       <label name="flagKH" class="tablinks btn btn-sm btn-primary btn-simple flagKH" id="1" onclick="dangky(event, 'khachhang')">
                        <input type="radio" class="d-none d-sm-none" name="options">
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Khách Hàng</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-gift-2"></i>
                        </span>
                      </label>
                      
                    </div>
                  </div>
                </div>
                
              </div>
              <!-- ============================= noi dung ====================== -->
              <div id="doanhthu" class="card-body tabcontent">
              <!-- title -->
              <form action="thongkeDT" method="post">
              <div class="row">
                    <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>Từ ngày</label>
                        <input onchange="ktngay()" id="startDate" type="date" class="form-control" placeholder="Company" value="${namBD}" name="ngayBD">
                      </div>
                    </div>
                    
                    <div class="col-md-3 pl-md-1">
                      <div class="form-group">
                        <label>Đến ngày</label>
                        <input onchange="ktngay()" id="endDate" type="date" class="form-control" placeholder="Last Name" value="${namKT}" name="ngayKT">
                      </div>
                   
                    </div>
                     <div class="col-md-3 pl-md-1">
	                	<div class="card-footer">
	                	<button  type="submit" id="timkiem" class=" btn btn-fill btn-primary">Tìm kiếm</button>
	              		</div>
	              	</div>
                  </div>
				</form>

					<div class="row">
						<div class="col-md-3 pr-md-1"></div>
						<div class="col-md-3 pr-md-1"></div>
						<div class="col-md-3 pr-md-1"></div>
						<div class="col-md-2 pr-md-1 ">
							<div class="form-group tim-icons icon-zoom-split ">
								<label>Tìm Kiếm</label> <input class=" form-control"
									id="myInput" type="text" placeholder="">
							</div>
						</div>

					</div>
					
				
				<script>
                  function ktngay(){
	                  var startDate = new Date( document.getElementById("startDate").value);
	                  var endDate = new Date(document.getElementById("endDate").value);
	
	                  if (startDate > endDate){
	                	  demo.showNotification('top','right','Ngày không đúng! Vui lòng nhập lại!','3');
	                	  document.getElementById("timkiem").setAttribute("disabled", "");
	                  }else  document.getElementById("timkiem").removeAttribute("disabled")  ;
                  }
                  </script>	
					<!-- end title --> 
               
                 
                 
                <div class="card-body">
                <div class="table-responsive ps">
                  <table class="table tablesorter " id="">
                    <thead class=" text-primary">
                      <tr>
                      <th>
                          Mã Thẻ
                        </th>
                        <th>
                          Họ Và Tên
                        </th>
                        
                        <th>
                          Lớp
                        </th>
                        <th>
                          Gói Tập
                        </th>
                        <th>
                          Ngày Đăng Ký <br>(năm/tháng/ngày)
                        </th>
                        <th class="text-center">
                          Giá Tiền
                        </th>
                      </tr>
                    </thead>
                    <tbody id="myTable">
                    <c:forEach var="info" items="${theServices}">
                      <tr>
                        <td>
                          ${info.maThe}
                        </td>
                        <td>
                          ${info.khachHang.tenKH}
                        </td>
                        <td>
                          ${info.goiTap.lopDV.tenLop}
                        </td>
                        <td>
                          1 ${info.goiTap.tenGoiTap}
                        </td>
                        <td>
                          ${info.ngayDK}
                        </td>
                        <td class="text-center">
                          ${info.goiTap.gia}
                        </td>
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                <div class="ps__rail-x" style="left: 0px; bottom: 0px;"><div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps__rail-y" style="top: 0px; right: 0px;"><div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
              </div>
           
              <div class="row">
                    <div style="text-align: center" class="col-md-12 pr-md-3">
                      <div class="form-group">
                        <label>Tổng Cộng</label>
                        <input id="tongtien" style="text-align: right;font-size: 20px;color:pink" type="text" class="form-control" disabled="" placeholder="Company" value="00 VND">
                        
                      </div>
                    </div>
                    
                    
                  </div>
              </div>
              
              
              <!-- =================================== end================= -->
              <div id="dichvu" class="card-body tabcontent">
               <!-- title -->
               <form action="thongkeDV", method="POST">
              <div class="row">
                    <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>Từ ngày</label>
                        <input onchange="ktngayDV()" id="startDateDV" name="ngayBD"  type="date" class="form-control" placeholder="Company" value="${namBDDV}">
                      </div>
                    </div>
                    
                    <div class="col-md-3 pl-md-1">
                      <div class="form-group">
                        <label>Đến ngày</label>
                        <input onchange="ktngayDV()" id="endDateDV" name="ngayKT" type="date" class="form-control" placeholder="Last Name" value="${namKTDV}">
                      </div>
                   
                    </div>
                    <div class="col-md-3 pl-md-1">
                      <div class="form-group">
                        <label>Tên lớp</label>
                        <select  style="background: #27293d" class="form-control form-select" name="tenLopDV" id="cars">
                        	<option hidden selected="selected">${tenLopDV}</option>
                        	lopDVs
                        	 <c:forEach var="info" items="${lopDVs}">
						   		<option>${info.tenLop}</option>
						    </c:forEach>
						  </select>
                        <!-- <input type="text"  placeholder="Last Name" value="dropdown"> -->
                      </div>
                   
                    </div>
                     <div class="col-md-3 pl-md-1">
	                	<div class="card-footer">
	                	<button id="timkiemDV" type="submit" class="btn btn-fill btn-primary">Tìm Kiếm</button>
	              		</div>
	              	</div>
                  </div>
                  </form>
                  <script>
                  function ktngayDV(){
	                  var startDate = new Date( document.getElementById("startDateDV").value);
	                  var endDate = new Date(document.getElementById("endDateDV").value);
	
	                  if (startDate > endDate){
	                	  demo.showNotification('top','right','Ngày không đúng! Vui lòng nhập lại!','3');
	                	  document.getElementById("timkiemDV").setAttribute("disabled", "");
	                  }else  document.getElementById("timkiemDV").removeAttribute("disabled")  ;
                  }
                  </script>	
                  
                 <!-- end title --> 
               <div class="row">
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-2 pr-md-1 ">
                      <div  class="form-group tim-icons icon-zoom-split "  >
                        <label>Tìm Kiếm</label>
                        <input class=" form-control"  id="myInput1"  type="text" placeholder="" >
                      </div>
                    </div>
                   
                  </div>
                 <!-- end title --> 
               
                 
                 
                <div class="card-body">
                <div class="table-responsive ps">
                  <table class="table tablesorter " id="">
                    <thead class=" text-primary">
                      <tr>
                      <th>
                          Mã Thẻ
                        </th>
                        <th>
                          Họ Và Tên
                        </th>
                        <th>
                          Gói Tập
                        </th>
                       <!--  <th class="text-center">
                          Số lần tập
                        </th> -->
                        <th >
                          Trạng thái
                        </th>
                       
                      </tr>
                    </thead>
                    <tbody id="myTable1">
                    <c:forEach var="info" items="${theServiceDV}">
                      <tr>
                        <td>
                          ${info.maThe}
                        </td>
                        <td>
                          ${info.khachHang.tenKH}
                        </td>
                        <td>
                         ${info.goiTap.tenGoiTap}
                        </td>
                       <!--  <td class="text-center">
                          10
                        </td> -->
                        <td >
                          ${info.trangThai}
                        </td>
                        
                      </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                <div class="ps__rail-x" style="left: 0px; bottom: 0px;"><div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps__rail-y" style="top: 0px; right: 0px;"><div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
              </div>
           
              <div class="row">
                    <div style="text-align: center" class="col-md-12 pr-md-3">
                      <div class="form-group">
                        <label>Số Lượng Thẻ</label>
                        <input style="text-align: center;font-size: 20px;color:pink" type="text" class="form-control" disabled="" placeholder="số lượng thẻ" value="${slThe}">
                      </div>
                    </div>
                    
                    
                  </div>
              </div>
              
              <!-- =========================================== end2 =========== -->
              
              <!-- =================================== end3================= -->
              <div id="khachhang" class="card-body tabcontent">
               	 <!-- title -->
               <form action="thongkeKH" method="post">
              <div class="row">
                    <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>Từ ngày</label>
                        <input onchange="ktngayKH()" id="startDateKH" type="date" class="form-control" placeholder="Company" name="ngayBD" value="${namBDDV}">
                      </div>
                    </div>
                    
                    <div class="col-md-3 pl-md-1">
                      <div class="form-group">
                        <label>Đến ngày</label>
                        <input onchange="ktngayKH()" id="endDateKH" type="date" class="form-control" placeholder="Last Name" name="ngayKT" value="${namKTDV}">
                      </div>
                   
                    </div>
                     
	              	
	              	
	              	<div class="col-md-3 pl-md-1">
                      <div class="form-group">
                        <label>Tên Khách Hàng</label>
                        <input type="text" class="form-control" placeholder="Last Name" value="${tenKH}" name="tenKH">
                      </div>
                  </div>
                  <div class="col-md-3 pl-md-1">
	                	<div class="card-footer">
	                	<button id="timkiemKH" type="submit" class="btn btn-fill btn-primary">Tìm Kiếm</button>
	              		</div>
	              	</div>
                  <!-- end title --> 
                </div>
                
                </form>
                
                <script>
                  function ktngayKH(){
	                  var startDate = new Date( document.getElementById("startDateKH").value);
	                  var endDate = new Date(document.getElementById("endDateKH").value);
	
	                  if (startDate > endDate){
	                	  demo.showNotification('top','right','Ngày không đúng! Vui lòng nhập lại!','3');
	                	  document.getElementById("timkiemKH").setAttribute("disabled", "");
	                  }else  document.getElementById("timkiemKH").removeAttribute("disabled")  ;
                  }
                  </script>	
               <div class="row">
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-3 pr-md-1">
                      
                    </div>
                    <div  class="col-md-2 pr-md-1 ">
                      <div  class="form-group tim-icons icon-zoom-split "  >
                        <label>Tìm Kiếm</label>
                        <input class=" form-control"  id="myInput2"  type="text" placeholder="" >
                      </div>
                    </div>
                   
                  </div>
                 <!-- end title --> 
                  
                 <!-- end title --> 
               <div class="card-body">
                <div class="table-responsive ps">
                  <table class="table tablesorter " id="">
                    <thead class=" text-primary">
                      <tr>
                        <th>
                          Mã Thẻ
                        </th>
                        <th>
                        Mã KH
                          
                        </th>
                        <th>
                          Họ Và Tên
                        </th>
                        <th>
                          Gói Tập
                        </th>
                        <!-- <th class="text-center">
                          Số Lần Tập
                        </th> -->
                        <th >
                          Email
                        </th>
                      </tr>
                    </thead>
                    <tbody id="myTable2">
                    <c:forEach var="info" items="${theServiceKH}">
                   
                      <tr>
                        <td>
                        	${info.maThe}
                          
                        </td>
                        <td>
                          ${info.khachHang.maKH}
                        </td>
                        <td>
                         ${info.khachHang.tenKH}
                        </td>
                        <td>
                          ${info.goiTap.lopDV.tenLop}
                        </td>
                        <!-- <td  class="text-center">
                          18
                        </td> -->
                        <td >
                         ${info.khachHang.email}
                        </td>
                      </tr>
                      </c:forEach>
                      
                    </tbody>
                  </table>
                <div class="ps__rail-x" style="left: 0px; bottom: 0px;"><div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps__rail-y" style="top: 0px; right: 0px;"><div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
              </div>
              <div class="row">
                    <div style="text-align: center" class="col-md-12 pr-md-3">
                      <div class="form-group">
                        <label>Số lượng</label>
                        <input style="text-align: center;font-size: 20px;color:pink" type="text" class="form-control" disabled="" placeholder="Số lượng khách hàng" value="${slTheKH}">
                      </div>
                    </div>
                    
                    
                  </div>
              </div>
              <!-- kiem tra cac nút thống kê -->
              <script>
					if("${flag}"=="kh"){
						document.getElementsByName("flagKH")[0].setAttribute("id", "defaultOpen");
						document.getElementsByName("flagKH")[0].classList.add("active");
					}else if("${flag}"=="dv"){
						document.getElementsByName("flagDV")[0].setAttribute("id", "defaultOpen");
						document.getElementsByName("flagDV")[0].classList.add("active");
					}else{
						document.getElementsByName("flagDT")[0].setAttribute("id", "defaultOpen");
						document.getElementsByName("flagDT")[0].classList.add("active");
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
          <div class="col-md-4">
            <div class="card card-user">
              <div class="card-body">
                <p class="card-text">
                  <div class="author">
                    <div class="block block-one"></div>
                    <div class="block block-two"></div>
                    <div class="block block-three"></div>
                    <div class="block block-four"></div>
                    <a href="javascript:void(0)">
                      <p id="tongtienlon" style="color:white;font-size: 40px"></p>
                      <h5 class="title"></h5>
                      <script>
	                        const formatter = new Intl.NumberFormat('en-US', {
	          				  style: 'currency',
	          				  currency: 'VND',
	          				  minimumFractionDigits: 2
	          				})
	          				if("${tongTien}".trim()!="") {
	                        	document.getElementById("tongtien").value= formatter.format(${tongTien});
	          					document.getElementById("tongtienlon").innerHTML= formatter.format(${tongTien});
	          				}else if("${slTheKH}".trim()!="") document.getElementById("tongtienlon").innerHTML="${slTheKH}";
	          				else document.getElementById("tongtienlon").innerHTML="${slThe}";
	          				
                        </script>
                    </a>
                    <p class="description">
                      Tổng Cộng
                    </p>
                  </div>
                </p>
                <!-- <div style="text-align: center" class="card-description">
                  Tổng số lượng khách hàng: 1000 (KH)
                </div>
                <div style="text-align: center" class="card-description">
                  Gói yoga: 100 (kh)
                </div>
                <div style="text-align: center" class="card-description">
                  Gói boxing: 100 (kh)
                </div>
                <div style="text-align: center" class="card-description">
                  Gói fitness: 100 (kh)
                </div> -->
              </div>
              
            </div>
          </div>
        </div>
      </div>
     
    </div>
  </div>
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