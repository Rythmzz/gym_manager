   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/include/sidebar.jsp" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8" %>  
      <%@ include file="/resources/include/navbarmn.jsp" %>
   
      
      <!- -->
     
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
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
  margin: 1% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
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


/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 48px;
  height: 24px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 16px;
  width: 16px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

.slider.round {
  border-radius: 24px;
}

.slider.round:before {
  border-radius: 50%;
}

.shakene{
  /* Start the shake animation and make the animation last for 0.5 seconds */
  animation: shake 2s; 
  
  /* When the animation is finished, start again */
  animation-iteration-count: infinite; 
  color:red;
}

@keyframes shake {
  0% { transform: translate(1px, 1px) rotate(0deg); }
  10% { transform: translate(-1px, -2px) rotate(-1deg); }
  20% { transform: translate(-3px, 0px) rotate(1deg); }
  30% { transform: translate(3px, 2px) rotate(0deg); }
  40% { transform: translate(1px, -1px) rotate(1deg); }
  50% { transform: translate(-1px, 2px) rotate(-1deg); }
  
  60% { transform: translate(-3px, 1px) rotate(0deg); }
  70% { transform: translate(3px, 1px) rotate(-1deg); }
  80% { transform: translate(-1px, -1px) rotate(1deg); }
  90% { transform: translate(1px, 2px) rotate(0deg); }
  100% { transform: translate(1px, -2px) rotate(-1deg); }
}
</style>

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
      
      <div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card ">
              <div class="card-header">
					<div class="row">
						<div class="col-md-3 pr-md-1"><h4 class="card-title"> Nhân Viên</h4>
							<div class="card-footer">
		                	<button type="submit" id="timkiem" onclick="document.getElementById('them').style.display='block'" class=" btn btn-fill btn-primary">Đăng ký</button>
		              		</div>
	              		</div>
						<div class="col-md-3 pr-md-1"></div>
						<div class="col-md-3 pr-md-1"></div>
						<div class="col-md-2 pr-md-1 ">
							<div class="form-group tim-icons icon-zoom-split ">
								<label>Tìm Kiếm</label> <input class=" form-control"
									id="myInput" type="text" placeholder="">
							</div>
						</div>

					</div>
					
              </div>
              
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
						<h5 class="title">Đăng ký nhân viên</h5>
					</div>
					
					<div class="col-sm-12">
					
					</div>
				</div>
				
				
	<!-- =============================== Form Lớp DV========================= -->			
				<div id="lopdv1" class="card-body tabcontent">
				
									
										
											<div id="profile" class="card-body tabcontent" style="display: block;">
					<form action="dangkynhanvien" method="POST">
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Họ Và Tên</label> <input type="text" class="form-control" placeholder="Họ và tên" required pattern="\S+.*" value="" name="hovaten">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								<div class="form-group">
									<label>Email</label> 
									<input type="email" class="form-control" placeholder="mike@email.com" name="email">
									
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 pr-md-1">
								<div class="form-group">
									<label>Số Điện Thoại</label> 
									
									<input type="text" class="form-control" placeholder="SĐT" value="" name = "sdt" pattern="(\+84|0)\d{9,10}"
									title="Số điện thoại gồm 10 chữ số (nếu nhập 0) hoặc 9 số (nếu nhập +84)">
								</div>
							</div>
							<div class="col-md-6 pl-md-1">
								
								<div class="form-group">
									<label>Giới tính</label> 
									<select class="form-control form-select" name="gioitinh">
										<option selected="selected" hidden="" style="background: #27293d" class="form-group"></option>
										<option style="background: #27293d" class="form-group">Nam</option>
										<option style="background: #27293d" class="form-group">Nữ</option>
									</select>
								</div>
							
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-8 pr-md-1">
								<div class="form-group">
									<label>Địa Chỉ</label> <input type="text" class="form-control" placeholder="Địa chỉ" value="" name="diachi">
									
								</div>
							</div>
							
							<!-- <div class="col-md-4 ">
								<div>
									<label>Ảnh</label> <input type="file" class="form-control" placeholder="Link ảnh" name="avatar">
									
								</div>
							</div> -->
							
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Chức vụ</label> 
									<select class="form-control form-select" name="chucvu">
										<option selected="selected" hidden="" style="background: #27293d" class="form-group"></option>
										<option style="background: #27293d" class="form-group">Chủ tịch</option>
										<option style="background: #27293d" class="form-group">Nhân viên</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4 pr-md-1">
								<div class="form-group">
									<label>Username</label> 
									<input type="text" class="form-control" placeholder="Username" value="" name="username">
								</div>
							</div>
							<div class="col-md-4 ">
								<div class="form-group">
									<label>Password</label> <input onchange="checkPass()" type="password" class="form-control" placeholder="Password" value="" name="password">
								</div>
							</div>
							<div class="col-md-4 ">
								<div class="form-group">
									<label id="xacnhanpasss">Xác nhận Password</label> <input id="xacnhanpasss1" onchange="checkPass()" type="password" class="form-control" placeholder="Xác nhận Password" value="" name="xnpassword">
								</div>
							</div>
						</div>
						
						<div class="row">
							
							<div class="col-md-8">
								<div class="form-group"><grammarly-extension data-grammarly-shadow-root="true" style="position: absolute; top: 0px; left: 0px; pointer-events: none;" class="cGcvT"></grammarly-extension><grammarly-extension data-grammarly-shadow-root="true" style="position: absolute; top: 0px; left: 0px; pointer-events: none;" class="cGcvT"></grammarly-extension>
									<label>Ghi chú</label>
									<textarea rows="1" cols="80" class="form-control" placeholder="Ghi chú thông tin khách hàng" value="Mike" spellcheck="false"></textarea>
								</div>
							</div>
						</div>
						<div class="card-footer">
						<button id="dangkynv" type="submit" class="btn btn-fill btn-primary">Lưu</button>
					</div>
					<script>
							function checkPass(){
								if(document.getElementsByName("password")[0].value!=document.getElementsByName("xnpassword")[0].value){
									document.getElementById("xacnhanpasss").setAttribute("class", "shakene");
									//document.getElementById("xacnhanpasss1").setAttribute("class", "shakene");
									document.getElementById("xacnhanpasss").style.color = "#FF8159";
									document.getElementById("dangkynv").setAttribute("disabled", "");
									
								}else{ 
									
									document.getElementById("xacnhanpasss").removeAttribute("class");
									document.getElementById("xacnhanpasss").style.color = null;
									document.getElementById("dangkynv").removeAttribute("disabled");
									}
								

							}
							
					</script>
					</form>
					
				</div>
										
								
									
				</div>
				              <!-- ===================end FormlớpDV============================== -->
		
              
					</div>
				</div>
		      <!-- ========================= end them ========================== -->
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table tablesorter " id="myTable1">
                    <thead class=" text-primary">
                      <tr>
                        <th>
                          MANV
                        </th>
                        <th>
                          Họ Và Tên
                        </th>
                        <th>
                          Giới Tính
                        </th>
                        <th>
                          Email
                        </th>
                        
                        
                        <th class="text-center">
                          Số Điện Thoại
                        </th>
                       
                        <th class="text-center">Chức vụ</th>
                        <th class="text-center">Chỉnh sửa</th>
                        <th class="text-center">Khóa tài khoản</th>
                      </tr>
                    </thead>
                    <tbody id="myTable" >
                     <c:forEach var="info" items="${nhanVien}">
                      <tr  id="tr_${info.maNV}" >
                        <td>
                          ${info.maNV}
                        </td>
                        <td>
                          ${info.tenNV}
                        </td>
                        <td >
                          ${info.gioiTinh}
                        </td>
                        <td >
                         ${info.email}
                        </td>
                        <td class="text-center">
                          ${info.sdt}
                        </td >
                       
                        
                        <td class="text-center">${info.taiKhoan.phanQuyen.chucVu}</td>
                        <td class="text-center text-success"><a  href="nhanvien?id=${info.maNV}" class="tim-icons icon-pencil"></a></td>
                        
                        <td id="switch_${info.maNV}" class="text-center">
                      		 <label class="switch">
							   <input id="khoa_${info.maNV}"  onchange="ajax_khoa_TKNV('${info.maNV}')" type="checkbox">
							   <span class="slider round"></span>
							 </label>
                      	</td>
                         
						<script>
							if("${info.taiKhoan.trangThai}".trim()=="0"){
								document.getElementById("khoa_${info.maNV}").setAttribute("checked", "");
							}
							function checkAdmins(id) {
					        	  var list = document.getElementById(id);
					        	  while (true){
					        	  if (list.hasChildNodes() && list.hasChildNodes()!=null ) {
					        	    list.removeChild(list.childNodes[0]);
					        	  }else break;
					        	  }
					        	}
					          if("${info.taiKhoan.phanQuyen.chucVu}".trim()=="Chủ tịch"){
					        	  checkAdmins("switch_${info.maNV}");
					        	  
					             }
						</script>                         
							
                         
                         
                                              
                      </tr>
                    </c:forEach> 
                    
                    <script>
						if("${thongbao}".trim()=="0") demo.showNotification('top','right','Đăng ký thành công!','2');
						else if("${thongbao}".trim()=="1")demo.showNotification('top','right','Password và Xác nhận Password không đúng!','4');
						else if("${thongbao}".trim()=="2")demo.showNotification('top','right','Email hoặc Username trùng nhau!','4');
						function ajax_khoa_TKNV(maNV){
							//alert(document.getElementById("khoa_"+maNV).checked);
							
							  $.ajax({
			                    url : "khoataikhoan",
			                    type : "post",
			                    dataType:"text",
			                    data : {
			                         "maNV": maNV,
			                         "checked":""+document.getElementById("khoa_"+maNV).checked
			                         
			                    },
			                    success : function (result){
				                    
			                        if(result=="1"){
				                        if(document.getElementById("khoa_"+maNV).checked==true) demo.showNotification('top','right','Khóa tài khoản thành công!','2');
				                        else demo.showNotification('top','right','Mở khóa tài khoản thành công!','2');
			                        	
			                        	
			                        	
				                     }else {demo.showNotification('top','right','Khóa tài khoản thất bại!','4');}
			                    	
			                    }
			                });  


						}


                    </script>
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
      x = rows[i].getElementsByTagName("TD")[0];
      y = rows[i + 1].getElementsByTagName("TD")[0];
      //alert(parseInt(x.innerHTML.split('KH')[1]));
      //alert(parseInt(y.innerHTML.split('KH')[1]));
      //check if the two rows should switch place:
      if (parseInt(x.innerHTML.split('NV')[1]) < parseInt(y.innerHTML.split('NV')[1])) {
      	//alert(parseInt(x.innerHTML.split('KH')[1]));
        //if so, mark as a switch and break the loop:
        shouldSwitch = true;
        break;
      }
    }
    //break;
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