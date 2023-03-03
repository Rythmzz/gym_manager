<%@ include file="/resources/include/sidebar.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!- -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<style type="text/css">

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 6; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.8); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  
}

</style>
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

</script>

  <%@ page contentType="text/html" pageEncoding="UTF-8" %>    
      <%@ include file="/resources/include/navbarmn.jsp" %>
      <div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header ">
                <div class="row">
                  <div class="col-sm-6 text-left">
                    
                    <h2 class="card-title"> Bảng Lớp</h2>
                  </div>
                  <div class="col-sm-6">
                    <div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
                      <label  class="tablinks btn btn-sm btn-primary btn-simple active" id="defaultOpen" >
                        <input type="radio" name="options" checked>
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Bảng Lớp</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-single-02"></i>
                        </span>
                      </label>               
                    </div>
                  </div>
                </div>
                
              </div>
              <div class="row">
				<div class="col-md-3 pl-md-3">
	                	<div class="card-footer">
	                	<button type="submit" class="btn btn-fill btn-primary" onclick="document.getElementById('them').style.display='block'" class="button">
							<span> Thêm </span>
						</button>
	              		</div>
	              	</div>
	              	<div class="col-md-6 pl-md-1"></div>
	              	<div  class="col-md-2 pr-md-1 ">
							<div class="form-group tim-icons icon-zoom-split ">
								<label>Tìm Kiếm</label> <input class=" form-control" id="myInput" type="text" placeholder="">
							</div>
						</div>
				</div>
              <!-- ========================Nội dung ======================== -->
              <div id="lopdv" class="card-body tabcontent">
					
                 <!-- end title --> 
               <div class="card-body">
                <div class="table-responsive ps">
                  <table class="table tablesorter " id="myTable1">
                    <thead class=" text-primary">
                      <tr>
                        <th>
                        Mã Lớp
                        </th>

                        <th> 
                        Tên Lớp
                        </th>
                  
                        <th>
                        Chỉnh sửa
                        </th>
                      </tr>
                    </thead>
                    <tbody id="myTable">
                    <c:forEach var="info" items="${lopDVServices}">
                   		
                      <tr >
                        <td>
                        ${info.maLop}
                          
                        </td>
                        <td>
                         ${info.tenLop}
                        
                        </td>
                         
                         <td><a  href="lopdv1?id=${info.maLop}" class="tim-icons icon-pencil"></a></td>
                      </tr>
                     
                      </c:forEach> 
                    </tbody>
                  </table>
                <div class="ps__rail-x" style="left: 0px; bottom: 0px;"><div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div></div><div class="ps__rail-y" style="top: 0px; right: 0px;"><div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px;"></div></div></div>
              </div>
                
                  </div>
 <script>
 function sortTable() {
	 
	  var table, rows, switching, i, x, y, shouldSwitch;
	  table = document.getElementById("myTable1");
	  switching = true;
	  /* Make a loop that will continue until
	  no switching has been done: */
	  while (switching) {
	    // Start by saying: no switching is done:
	    switching = false;
	    rows = table.rows;
	    /* Loop through all table rows (except the
	    first, which contains table headers): */
	    for (i = 1; i < (rows.length - 1); i++) {
	      // Start by saying there should be no switching:
	      shouldSwitch = false;
	      /* Get the two elements you want to compare,
	      one from current row and one from the next: */
	      x = rows[i].getElementsByTagName("TD")[0];
	      y = rows[i + 1].getElementsByTagName("TD")[0];
	      // Check if the two rows should switch place:
	     // alert(x.innerHTML.trim().substr(2));
	      if (parseInt(x.innerHTML.trim().substr(2)) < parseInt(y.innerHTML.trim().substr(2))) {
	        // If so, mark as a switch and break the loop:
	        shouldSwitch = true;
	        break;
	      }
	    }
	//    break;
	    if (shouldSwitch) {
	      /* If a switch has been marked, make the switch
	      and mark that a switch has been done: */
	      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
	      switching = true;
	    }
	  }
	}
 sortTable();
</script>
              
              <!-- =========================================== end2 =========== -->

              <!-- ==========================Form Thêm======================= -->

              	<div id="them" class="modal row">
              	
				<div class="modal-content animate card-body  col-md-5"
									style="background: #27293d">
				<div class="card-header">
				<div class="imgcontainer">
						<span
							onclick="document.getElementById('them').style.display='none'"
							class="close" title="Close Modal">&times;</span>
				</div>
					<div class="col-sm-6 text-left">
						<h5 class="title">Lớp mới</h5>
					</div>
					
					<div class="col-sm-12">
					
					</div>
				</div>
				
				
	<!-- =============================== Form Lớp DV========================= -->			
				<div id="lopdv1" class="card-body tabcontent">
				
									<form action="lopdv" method="post">
										
											<div class="row">
											
											<div class="col-md-6 px-md-1">
												<label>Tên Lớp</label> <input type="text" list="ide"
														class="form-control" placeholder="Tên lớp" name="tenlop"
														value="" required pattern="\S+.*">
												
											</div>
											</div>
											
											<div class="row">
												<div  class="col-md-12 pr-md-1">
													<label> Bạn muốn dùng 4 gói tập mặc định</label>
													<ul> 
													<li>
														<div class="row">
														
														<div style="margin-top: 10px" class="col-md-3">
														
														<input id="checkboxngay" onchange="hienthigia('checkboxngay','removengay')" style="width:15px;height:15px "  name="ngay" value="ngay" type="checkbox"  />
															<span>1 Ngày</span>
															
														</div>
															
														
														
															<div id="removengay" hidden class="col-md-7" style="width:200px" class="form-group">
																 <input type="number"
																	class="form-control" placeholder="gia" name="giangay">
															</div>
														</div>
														
														
													</li>
													<br>
												
													<li>
														
														<div class="row">
														
														<div style="margin-top: 10px" class="col-md-3">
														
														<input id="checkboxtuan" onchange="hienthigia('checkboxtuan','removetuan')" style="width:15px;height:15px "  name="tuan" value="tuan"  type="checkbox"  />
															<span>1 Tuần</span>
															
														</div>
															
														
														
															<div id="removetuan" hidden class="col-md-7" style="width:200px" class="form-group">
																 <input type="number"
																	class="form-control" placeholder="gia" name="giatuan">
															</div>
														</div>
													</li>
													<br>
													
													<li>
														
														<div class="row">
														
														<div style="margin-top: 10px" class="col-md-3">
														
														<input id="checkboxthang" onchange="hienthigia('checkboxthang','removethang')" style="width:15px;height:15px "  name="thang" value="thang"  type="checkbox"  />
															<span>1 Tháng</span>
															
														</div>
															
														
														
															<div id="removethang" hidden class="col-md-7" style="width:200px" class="form-group">
																 <input type="number"
																	class="form-control" placeholder="gia" name="giathang">
															</div>
														</div>
													</li>
													<br>
													
													<li>
														
														<div class="row">
														
														<div style="margin-top: 10px" class="col-md-3">
														
														<input id="checkboxnam" onchange="hienthigia('checkboxnam','removenam')" style="width:15px;height:15px "  name="nam"  value="nam" type="checkbox"  />
															<span>1 Năm</span>
															
														</div>
															
														
														
															<div id="removenam" hidden class="col-md-7" style="width:200px" class="form-group">
																 <input type="number"
																	class="form-control" placeholder="gia" name="gianam">
															</div>
														</div>
													</li>
													</ul>	
												</div>
												
											<script>
													function hienthigia(id,idremove){
														if(document.getElementById(id).checked){
															document.getElementById(idremove).removeAttribute("hidden");
															
														}else document.getElementById(idremove).setAttribute("hidden","");

													}

											</script>
											
									</div>
										<div class="card-footer">
										<button type="submit" class="btn btn-fill btn-primary">Thêm</button>
									</div>
									</form>
									
				</div>
				              <!-- ===================end FormlớpDV============================== -->
		
              
					</div>
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
		
		if("${thongbao}".trim()=="1")demo.showNotification('top','right','Thêm lớp thành công!','2');
		else if("${thongbao}".trim()=="0") demo.showNotification('top','right','Thêm lớp thất bại!','4');
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

