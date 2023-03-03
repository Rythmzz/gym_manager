<%@ include file="/resources/include/sidebar.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!- -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
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

 <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/JNKKKK/MoreToggles.css@0.2.1/output/moretoggles.min.css">
  </head>

  <%@ page contentType="text/html" pageEncoding="UTF-8" %>    
      <%@ include file="/resources/include/navbarmn.jsp" %>
      <div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header ">
                <div class="row">
                  <div class="col-sm-6 text-left">
                    
                    <h2 class="card-title"> Lớp Dịch Vụ</h2>
                  </div>
                  <div class="col-sm-6">
                    <div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
                      <label  class="tablinks btn btn-sm btn-primary btn-simple active" id="defaultOpen" onclick="dangky(event, 'doanhthu')">
                        <input type="radio" name="options" checked>
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Lớp Dịch vụ</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-single-02"></i>
                        </span>
                      </label>               
                    </div>
                  </div>
                </div>
                
              </div>

              <!-- ========================Nội dung ======================== -->
              <!-- ========================Chỉnh sửa lớp dịch vụ============ -->
              <div id="chinhsua" class="card-body tabcontent">
					<form>
						<c:forEach var="info" items="${lopDVs}">
                  
                       <div class="row">
											<div class="col-md-3 pr-md-1">
												<div  class="form-group">
										<label>Mã lớp</label> <input style="text-align: center;color:white" type="text"
											class="form-control" disabled="" placeholder="ID" name="maLop" id="maLop"
											value=" ${info.maLop}">
									</div>
											</div>
											<div class="col-md-3 pr-md-1">
												<label>Tên Lớp</label> 
												<input type="text" list="ide"
														class="form-control" placeholder="tenLop" name="tenLop"
														value="${info.tenLop}">
												<datalist id="ide">
													<option value="boxing" label="mặc định" />
												    <option value="erobic" label="mặc định" />
												    <option value="fitness"label="mặc định"/>
												    <option value="yoga" label="mặc định"/>
												</datalist>
												</div>
							</div>
							 </c:forEach> 
						</form>	 
					<!-- ================================end lớp dv=============================== -->
					
	<!-- ==========================================Thêm mới gói tập========================================= -->						 
							 <form action="updatelopdv" method="post">
							
							<div class="row">
											
											
												
											<div class="col-md-3 pr-md-1">
												<div class="form-group">
													<label>Loại gói tập</label>
													<select id="loaigoitap"  onchange="chonloaigoitap()" placeholder="Hãy chọn loại gói tập" style="background: #27293d" class="form-control form-select">
														<option hidden checked="" "></option>
														<option>Ngày</option>
														<option>Tuần</option>
														<option>Tháng</option>
														<option>Năm</option>
														
													</select>
												</div>
											</div>
											 <div  class="col-md-2 pr-md-1">
											      <div class="form-group">
											           <label>Tên gói tập</label>
											          <select id="optiongoitap" name="tengoitap"  style="background: #27293d" class="form-control form-select"></select>
											      </div>
											</div> 

								
											<script>

												function layoption(start,end,ten){
													var options="";
													for(var  loaigoitap = start ; loaigoitap <=end; loaigoitap++){ 
														options += "<option>"+loaigoitap+" "+ten +"</option>";
													}
													return options;
												}
												function  chonloaigoitap(){
													
													if(document.getElementById("loaigoitap").value.trim()=="Ngày"){
														
														document.getElementById("optiongoitap").innerHTML=layoption(1,6,"ngày");
													}else if(document.getElementById("loaigoitap").value.trim()=="Tháng"){
														
														document.getElementById("optiongoitap").innerHTML=layoption(1,11,"tháng");
													}else if(document.getElementById("loaigoitap").value.trim()=="Tuần"){
													
														document.getElementById("optiongoitap").innerHTML=layoption(1,3,"tuần");
													}else if(document.getElementById("loaigoitap").value.trim()=="Năm"){
														
														document.getElementById("optiongoitap").innerHTML=layoption(1,10,"năm");
													}
													
												}
											</script>
									
										
										<div class="col-md-3 pr-md-1">
												<div class="form-group">
													<label>Giá</label> <input type="number"
														class="form-control" placeholder="gia" name="gia">
												</div>
											</div>
									
									<input name = "malop" value="" id = "maLop1" hidden="">	
									<div class="card-footer">
										<button type="submit" class="btn btn-fill btn-primary">Thêm</button>
									</div>
						</div>	
						
					</form>
					
					
					<script type="text/javascript">
						document.getElementById("maLop1").value = document.getElementById("maLop").value;
						
					</script>
				
                 <!-- end title --> 
              
              <!-- =================================end==================================================== -->
              <!-- =================================Bảng gói tập=========================================== -->
              
              <div class="card-body">
                <div class="table-responsive ps">
                  <table class="table tablesorter " id="">
                    <thead class=" text-primary">
                      <tr>
                      <th>
                          Mã Gói Tập
                        </th>
                        <th>
                          Tên Gói tập
                        </th>
                        
                        <th class="text-center">
                          Thời hạn
                        </th>
                        <th class="text-center">
                          Giá
                        </th>
                       <th class="text-center">Trạng Thái</th>
                        <th class="text-center">
                          Khóa gói tập
                        </th>
                        <th class="text-center">
                          Xóa
                        </th>
                        
                        
                      </tr>
                    </thead>
                    <tbody id="myTable">
						<c:forEach var="info" items="${goiTapServices}">
                      <tr id="tr_${info.maGoiTap}">
                        <td>
                          ${info.maGoiTap}
                        </td>
                        <td>
                          ${info.tenGoiTap}
                        </td>
                        <td class="text-center">
                          ${info.thoiHan}
                        </td>
                        <td class="text-center">
                          ${info.gia} VND
                        </td>
                        <td id="td_${info.maGoiTap}" class="text-center">
                        	<span id="ttgt_${info.maGoiTap}"> 	
                        		<c:forEach var="the" items="${info.the}">
                        			${the.maThe},
                        		</c:forEach>
							</span>
                        	<script>
								var trangthai = "";
								var trangthais = document
										.getElementById("ttgt_${info.maGoiTap}").innerHTML
										.trim();
								if (trangthais == "") {
									trangthai = "KDK";
								} else {
									trangthai = "DK";
								}
								
								document.getElementById("td_${info.maGoiTap}").innerHTML = trangthai.trim();
								
								
							</script>
                        
                        </td>
                        <td class="text-center">
                      		 <label class="switch">
							   <input id="khoa_${info.maGoiTap}" onchange="ajax_khoa_GoiTap('${info.maGoiTap}')" type="checkbox">
							   <span class="slider round"></span>
							 </label>
                      	</td>
                         
                         <td  class="text-center text-danger"><a href="#myModal${info.maGoiTap}" data-toggle="modal"  id="xoa1_${info.maGoiTap}" style="color:red;cursor: pointer;" class="tim-icons icon-simple-remove"></a></td>
                      	 <!-- Modal HTML -->
							<div id="myModal${info.maGoiTap}" class="modal fade">
								<div class="modal-dialog modal-confirm">
									<div class="modal-content">
										
													<br>		
											<center><h3 style="color: black">Bạn muốn tiếp tục?</h3></center>	
							                
										
										<div class="modal-body">
											<center><p> BẠN chắc chắn muốn xóa gói tập <b style="font-weight: bold;">${info.tenGoiTap}</b> ?</p></center>
										</div>
										<div class="modal-footer">
											<button id="thoat${info.maGoiTap}" style="margin-left: 100px" type="button" class="btn btn-info" data-dismiss="modal">Thoát</button>
											<button onclick='ajax_xoa_GT("${info.maGoiTap}")' style="margin-right: 100px" type="button" class="btn btn-danger">Xóa</button>
										</div>
									</div>
								</div>
							</div>     
                      </tr>
                      <script>
                         if(document.getElementById("td_${info.maGoiTap}").innerHTML.trim()!="KDK") document.getElementById("xoa1_${info.maGoiTap}").remove(); 

                         if("${info.trangThai}".trim()=="0"){
								document.getElementById("khoa_${info.maGoiTap}").setAttribute("checked", "");
							}
                         
                         </script>
                      </c:forEach>
                    </tbody>
                  </table>
            </div>
          </div>
   </div>
 </div>       
 
 <script>
						if("${thongbao}".trim()=="0") demo.showNotification('top','right','Đăng ký thành công!','2');
						else if("${thongbao}".trim()=="1")demo.showNotification('top','right','Password và Xác nhận Password không đúng!','4');
						else if("${thongbao}".trim()=="2")demo.showNotification('top','right','Email hoặc Username trùng nhau!','4');
						function ajax_khoa_GoiTap(maGT){
							//alert(document.getElementById("khoa_"+maNV).checked);
							
							  $.ajax({
			                    url : "khoagoitap",
			                    type : "post",
			                    dataType:"text",
			                    data : {
			                         "maGT": maGT,
			                         "checked":""+document.getElementById("khoa_"+maGT).checked
			                         
			                    },
			                    success : function (result){
				                    
			                        if(result=="1"){
				                        if(document.getElementById("khoa_"+maGT).checked==false)
			                        		demo.showNotification('top','right','Mở khóa gói tập thành công!','2');
				                        else 
				                        	demo.showNotification('top','right','Khóa gói tập thành công!','2');
			                        	
			                        	
				                     }else {demo.showNotification('top','right','Khóa gói tập thất bại!','4');}
			                    	
			                    }
			                });  


						}

						function ajax_xoa_GT(maGT){
							$.ajax({
			                    url : "xoagoitap",
			                    type : "post",
			                    dataType:"text",
			                    data : {
			                         "maGT": maGT
			                         
			                    },
			                    success : function (result){
				                    
			                        if(result=="1"){
			                        	document.getElementById("tr_"+maGT).remove();
			                        	document.getElementById("thoat"+maGT).click()
			                        	demo.showNotification('top','right','Xóa gói tập thành công!','2');
			                        	
			                        	
			                        	
				                     }else {demo.showNotification('top','right','Xóa gói tập thất bại!','4');}
			                    	
			                    }
			                });  
	
						}

                    </script>
 
 
 
 
           <!-- =================================end==================================================== -->
<!--           <div class="col-md-4">
            <div class="card card-user">
              <div class="card-body">
                <p class="card-text">
                  <div class="author">
                    <div class="block block-one"></div>
                    <div class="block block-two"></div>
                    <div class="block block-three"></div>
                    <div class="block block-four"></div>
                    <a href="javascript:void(0)">
                      <p style="color:white;font-size: 40px">$10000</p>
                      <h5 class="title"></h5>
                    </a>
                    <p class="description">
                      Tổng Cộng
                    </p>
                  </div>
                </p>
                <div style="text-align: center" class="card-description">
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
                </div>
              </div>
              <div class="card-footer">
                <div class="button-container">
                  <button href="javascript:void(0)" class="btn btn-icon btn-round btn-facebook">
                    <i class="fab fa-facebook"></i>
                  </button>
                  <button href="javascript:void(0)" class="btn btn-icon btn-round btn-twitter">
                    <i class="fab fa-twitter"></i>
                  </button>
                  <button href="javascript:void(0)" class="btn btn-icon btn-round btn-google">
                    <i class="fab fa-google-plus"></i>
                  </button>
                </div>
              </div>
            </div>
          </div> -->
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
	if("${thongbao}".trim()=="1") demo.showNotification('top','right','Thêm gói tập thành công!','2');
	else if("${thongbao}".trim()=="0")demo.showNotification('top','right','Thêm gói tập thất bại!','4');

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

