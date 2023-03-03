<%@ include file="/resources/include/sidebar.jsp" %>
  <%@ page contentType="text/html" pageEncoding="UTF-8" %>    
      <%@ include file="/resources/include/navbarmn.jsp" %>
      <div class="content">
        <div class="row">
          <div class="col-md-8">
            <div class="card">
              <div class="card-header ">
                <div class="row">
                  <div class="col-sm-6 text-left">
                    <h2 class="card-title">Bảng Điểm danh</h2>
                  </div>
                  <div class="col-sm-6">
                    <!-- <div class="btn-group btn-group-toggle float-right" data-toggle="buttons">
                      <label  class="tablinks btn btn-sm btn-primary btn-simple active" id="defaultOpen" onclick="Diemdanh(event, 'diemdanh')">
                        <input type="radio" name="options" checked>
                        <span class="d-none d-sm-block d-md-block d-lg-block d-xl-block">Điểm danh</span>
                        <span class="d-block d-sm-none">
                          <i class="tim-icons icon-single-02"></i>
                        </span></label>
                    </div> -->
                  </div>
                </div>
              </div>
              <!-- ============================= noi dung ====================== -->
<!--               ===================================tìm kiếm ID thẻ======================= -->
              <div id="diemdanh" class="card-body tabcontent">
                <form action="diemdanh" method="post" id="formdiemdanh">
                  <div class="row">
                    <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>Mã thẻ </label>
                        <input onchange="apigetinfoDD()" type="search" class="form-control"placeholder="Nhập mã thẻ" value="" name="mathe">
                      </div>
                    </div>
                    </div>
                    <div class="row">
                    <div class="col-md-4 ">
                      <div class="form-group">
                        <label>Họ và tên</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="Hoten" value="" name="hovaten">
                      </div>
                    </div>
                    
                    
                    <div class="col-md-4 ">
                      <div class="form-group">
                        <label>Giới tính</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="Giới tính" value="" name="gioitinh">
                      </div>
                    </div>
                    <div class="col-md-4 pl-md-1">
                     
                      <div class="form-group">
                        <label>Thời gian kết thúc (Tháng,ngày,năm)</label>
                        <input disabled="" style="color:white" name="ngaykt"  type="date" class="form-control" placeholder="Thời gian kết thúc"  value="" >
                      </div>
                    </div>
                    
                    
                  </div>
                  
                  
                  
                  <div class="row">
                    
                    <div class="col-md-6 pl-md-3">
                      <div class="form-group">
                        <label>Số điện thoại</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="sdt" value="" name="sdt">
                      </div>
                    </div>
                  </div>
<!--                   <div class="row"> -->
<!--                     <div class="col-md-12"> -->
<!--                       <div class="form-group"> -->
<!--                         <label>Địa chỉ</label> -->
<!--                         <input type="text" class="form-control" placeholder="Home Address" value="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09"> -->
<!--                       </div> -->
<!--                     </div> -->
<!--                   </div> -->
                  <div class="row">
                    <div class="col-md-4 pr-md-1">
                      <div class="form-group">
                        <label>Gói tập</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="Gói tập" value="" name="tengoitap">
                      </div>
                    </div>
                    <div class="col-md-4 px-md-1">
                      <div class="form-group">
                        <label>Trạng thái</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="Trạng thái" value="" name="trangthai">
                      </div>
                    </div>
                    <div class="col-md-3 px-md-1">
                      <div class="form-group">
                        <label>Số lần tập</label>
                        <input disabled="" style="color:white" type="text" class="form-control" placeholder="solantap" value="" name="solantap">
                      </div>
                    </div>
                  </div>
                </form>
                <div class="card-footer">
                <button id="diemdanhsubmit" type="submit" class="btn btn-fill btn-primary">Điểm Danh</button>
              </div>
              </div>
             
              
              <!-- =========================================== end =============================== -->
              
              
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
                      <img name="avatar" class="avatar" src="../resources/assets/img/emilyz.jpg" alt="...">
                      <h5 id="makh" class="title">Khách hàng thân thiết</h5>
                    </a>
                    
                  </div>
                </p>
                
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
		if("${thongbao}".trim()=="1")  demo.showNotification('top','right','Điểm danh thành công!','2');
  
                  function apigetinfoDD(){
						var mathe = document.getElementsByName("mathe")[0].value.toUpperCase();
						$.ajax({
		                    url : "getinfodiemdanh",
		                    type : "post",
		                    dataType:"text",
		                    data : {
		                         "mathe": mathe
		                    },
		                    success : function (result){
			                   // alert(result.search(/<|>|html/i));
		                    	if(result.search(/<|>|html/i)>-1 || result.trim()==""  ){
		                    		alert(0); 	
		                    		result='[,,,,,,,,]';
		                    		demo.showNotification('top','right','Thẻ không hợp lệ! Vui lòng nhập lại mã thẻ.','3');
		                    		document.getElementById("diemdanhsubmit").setAttribute("disabled","");
		                    	}else document.getElementById("diemdanhsubmit").removeAttribute("disabled");
				                    
			                        var arrayinfo=result;
			                       
			                        var res = arrayinfo.replace("[","").replace("]","").split(",");
			                        
			                        document.getElementsByName("hovaten")[0].value=res[0];
			                        document.getElementsByName("gioitinh")[0].value=res[1];
			                        document.getElementsByName("sdt")[0].value=res[2];
			                        document.getElementsByName("tengoitap")[0].value=res[3];
			                        document.getElementsByName("ngaykt")[0].value=res[4].trim();
			                        document.getElementsByName("trangthai")[0].value=res[5];
			                        document.getElementsByName("solantap")[0].value=res[6];
			                        document.getElementById("makh").innerHTML=res[7];
			                       
			                        if(res[8].trim()!="null") document.getElementsByName("avatar")[0].src="../resources/images/"+res[8].trim();
			                        else document.getElementsByName("avatar")[0].src="../resources/assets/img/emilyz.jpg";
			                        if(document.getElementsByName("trangthai")[0].value.trim()=="Hết Hạn"){
			                        	document.getElementById("diemdanhsubmit").innerHTML="Đăng ký dịch vụ";
			                        	document.getElementById("diemdanhsubmit").setAttribute("onclick","dangkydichvu()");	
			                        	demo.showNotification('top','right','Thẻ đã hết hạn! Vui lòng đăng ký dịch vụ mới.','3');
				                        	
					                }else if(document.getElementsByName("trangthai")[0].value.trim()=="Chưa Thanh Toán"){
					                	document.getElementById("diemdanhsubmit").innerHTML="Thanh toán hóa đơn";
					                	document.getElementById("diemdanhsubmit").setAttribute("onclick","thanhtoanhoadon()");
					                	demo.showNotification('top','right','Thẻ chưa được thanh toán! Vui lòng thanh toán trước.','3');
						             }else if(document.getElementsByName("trangthai")[0].value.trim()=="Hoạt Động"){
						            	 document.getElementById("diemdanhsubmit").innerHTML="Điểm danh";
						            	 document.getElementById("diemdanhsubmit").setAttribute("onclick","diemdanhthe()");
						            	 demo.showNotification('top','right','Thẻ hợp lệ! Bạn có thể điểm danh ngay bây giờ.','2');
							            }
		                    
		                    }
		                });

					
                  }
                  function dangkydichvu(){
                	  
						window.location.href="dichvu?id="+document.getElementById("makh").innerHTML.toUpperCase();
						}
                  function thanhtoanhoadon(){
                	  
						window.location.href="hoadon?id="+document.getElementsByName("mathe")[0].value.toUpperCase();
						}
                  function diemdanhthe(){
                	  document.getElementById("formdiemdanh").submit();
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