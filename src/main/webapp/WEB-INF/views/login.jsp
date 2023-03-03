<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="resources/css/login.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.center {
  margin: auto;
  width: 60%;
  border: 3px solid #73AD21;
  padding: 10px;
}
.centerLogo{
 margin: auto;
  width: 35%;
  padding: 15px;
  
}
img {
  border-radius: 50%;
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}
</style>
      
<body>
    <div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
    <div class="card card0 border-0">
        <div class="row d-flex">
            <div class="center">
                    <form action="login" method="post">
                    <img src="resources/images/gymer.jpg" width="150" height="150">
                    <div class="row px-3"> <label class="mb-1">
                            <h6 class="mb-0 text-sm">Tài khoản</h6>
                        </label> <input class="mb-4" type="text" name="username" placeholder="Enter a valid email address"> </div>
                    <div class="row px-3"> <label class="mb-1">
                            <h6 class="mb-0 text-sm">Mật khẩu</h6>
                        </label> <input type="password" name="password" placeholder="Enter password"> </div>
                    <div class="row px-3 mb-4">
                        <div class="custom-control custom-checkbox custom-control-inline"> <input id="chk1" type="checkbox" name="chk" class="custom-control-input"> <label for="chk1" class="custom-control-label text-sm">Ghi nhớ đăng nhập</label> </div> <a href="#" class="ml-auto mb-0 text-sm">Quên mật khẩu?</a>
                    </div>
                    <div class="centerLogo"> <button type="submit" class="btn btn-blue text-center">Đăng nhập</button> </div>
                    <div class="row mb-3 px-3"> <p><h6  style="color:red">${error}</h6></p> </div>
                    </form>
                      <div class="row px-3 mb-4">
                        <div class="line"></div> <small class="or text-center">Or</small>
                        <div class="line"></div>
                    </div>
                   <div class ="centerLogo">
                    <div class="row mb-4 px-3" >
                        <div class="facebook text-center mr-3" >
                            <div style="padding-top: 5px" class="fa fa-facebook"></div>
                        </div>
                        <div class="twitter text-center mr-3">
                            <div style="padding-top: 5px" class="fa fa-twitter"></div>
                        </div>
                        <div  class="linkedin text-center mr-3">
                            <div style=" padding-top: 4px" class="fa fa-linkedin"></div>
                        </div>
                </div>
                   </div>
            </div>
        </div>
        
    </div>
</div>
</body>