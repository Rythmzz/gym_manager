   <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/resources/include/sidebar.jsp" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8" %>  
      <%@ include file="/resources/include/navbarmn.jsp" %>
   
      
      <!- -->
     
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<style>


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
						<div class="col-md-3 pr-md-1"><h4 class="card-title"> Khách Hàng</h4></div>
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
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table tablesorter " id="myTable1">
                    <thead class=" text-primary">
                      <tr>
                        <th>
                          MAKH
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
                        
                        <th class="text-center">Trang thái</th>
                        <th class="text-center">
                          Số Điện Thoại
                        </th>
                        <th class="text-center">Dịch vụ</th>
                        <th class="text-center">Chỉnh sửa</th>
                        <th class="text-center">Xóa</th>
                      </tr>
                    </thead>
                    <tbody id="myTable" >
                    <c:forEach var="info" items="${customerServices}">
                      <tr  id="tr_${info.maKH}" >
                        <td>
                          ${info.maKH}
                        </td>
                        <td>
                          ${info.tenKH}
                        </td>
                        <td >
                          ${info.gioiTinh}
                        </td>
                        <td >
                         ${info.email}
                        </td>
                        <td id="td_${info.maKH}" class="text-center">
                        	<span id="ttdv_${info.maKH}">	
                        		<c:forEach var="the" items="${info.the}">
                        			${the.trangThai},
                        		</c:forEach>
							</span>
							<script>
								var trangthai = "";
								var trangthais = document
										.getElementById("ttdv_${info.maKH}").innerHTML
										.trim();
								if (trangthais == "") {
									trangthai = "DV";
								} else {
									if (trangthais.search("Hoạt Động") > -1){
										trangthai = trangthai + " HD";
									}
									if (trangthais.search("Chưa Thanh Toán") > -1)
									{	trangthai = trangthai + " CTT";
										document.getElementById("tr_${info.maKH}").setAttribute("class", "alert alert-warning");
										
									}
									if (trangthais.search("Hết Hạn") > -1){
										trangthai = trangthai + " HH";
									}
								}
								
								document.getElementById("td_${info.maKH}").innerHTML = trangthai.trim();
								
								
							</script>

										</td >
                        <td class="text-center">
                          ${info.sdt}
                        </td >
                        <td class="text-center"><a href="dichvu?id=${info.maKH}" class="tim-icons icon-badge"></a></td>
                        <td class="text-center text-success"><a  href="user?id=${info.maKH}" class="tim-icons icon-pencil"></a></td>
                        
                         <td class="text-center text-danger"><a href="#myModal${info.maKH}" data-toggle="modal"  id="xoa1_${info.maKH}" style="color:red;cursor: pointer;" class="tim-icons icon-simple-remove"></a></td>
                         
							                         <!-- Modal HTML -->
							<div id="myModal${info.maKH}" class="modal fade">
								<div class="modal-dialog modal-confirm">
									<div class="modal-content">
										
													<br>		
											<center><h3 style="color: black">Bạn muốn tiếp tục?</h3></center>	
							                
										
										<div class="modal-body">
											<center><p> BẠN chắc chắn muốn xóa khách hàng <b style="font-weight: bold;">${info.tenKH}</b> ?</p></center>
										</div>
										<div class="modal-footer">
											<button id="thoat${info.maKH}" style="margin-left: 100px" type="button" class="btn btn-info" data-dismiss="modal">Thoát</button>
											<button onclick='ajax_xoa_KH("${info.maKH}")' style="margin-right: 100px" type="button" class="btn btn-danger">Xóa</button>
										</div>
									</div>
								</div>
							</div>     
                         
                         
                         <script>
                         if(document.getElementById("td_${info.maKH}").innerHTML.trim()!="DV") document.getElementById("xoa1_${info.maKH}").remove(); 
                         </script>                      
                      </tr>
                    </c:forEach>
                    
                    <script>
						function ajax_xoa_KH(maKH){
							
							$.ajax({
			                    url : "xoakhachhang",
			                    type : "post",
			                    dataType:"text",
			                    data : {
			                         "maKH": maKH
			                         
			                    },
			                    success : function (result){
				                    
			                        if(result=="1"){
			                        	demo.showNotification('top','right','Xóa thành công!','2');
			                        	document.getElementById("tr_"+maKH).remove();
			                        	document.getElementById("thoat"+maKH).click()
			                        	
			                        	
				                     }else {demo.showNotification('top','right','Xóa thất bại!','4');}
			                    	
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
      if (parseInt(x.innerHTML.split('KH')[1]) < parseInt(y.innerHTML.split('KH')[1])) {
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