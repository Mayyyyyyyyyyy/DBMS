<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>


<head>
 
  

  <title>�D¾���x</title>
  <style>
    .header{
            display: flex;
            background-color: rgb(184, 176, 176);
        }

      .header nav{
          display: flex;
          width: 100%;
          justify-content: flex-end;
          line-height: 25px;
          font-size: 0px;
      }

      .header nav a{
            display: inline-block;
            padding: 10px;
            color: blue;
            font-size: 16px;
        }

      

      .search{
          width: 300px;
          vertical-align: middle;
          white-space: nowrap;
          position: relative;
      }
        
    .search input#search{
        width: 300px;
        height: 50px;
        background: #e7e9ec;
        border: none;
        font-size: 10pt;
        float: left;
        color: #63717f;
        padding-left: 45px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }
    .search .icon{
        position: absolute;
        top: 50%;
        margin-left: 13px;
        margin-top: 5px;
        z-index: 1;
        color: #676a70;
        width: 10px;
        border: none;
      }
    .box{
        display: inline-block;
        position: absolute;
        top: 70px;
        left: 420px;
            
    }

    /* CSS �{���X */
  </style>
</head>

<body>
  <div class="header">
    <a class="logo" href="#">
        <img src="1200x630wa.png" alt="">
    </a>
    <nav>
        <a href="Homepage.jsp">����</a>
        <a href="Resume.jsp">�i���޲z</a>
        <a href="Login.jsp">�n�J</a>
        <a href="Register.jsp">���U</a>
    </nav>
    
</div>
  

  <main>

    
    <div class="box">
      <div class="search">
        <span class="icon"><i class="fa fa-search"></i></span>
          <input
              type="search"
              id="search"
              placeholder="Search..."
          />
          <span class = "icon"><button type="submit"><image src = "search2.png"></image></button></span>
      </div>
    </div>
    
    
    
        <h2>�z�����</h2>
        <!-- �z������� -->
      </div>
    </div>
    <div>
      <!-- �j�M���G�C�� -->
      <!-- �C�ӷj�M���G��ܡG���D�B���q�B�a�I�B�p�T���s�B���ë��s -->
    </div>
  </main>

  <footer>
    <p>���v�n�� | �p���ڭ�</p>
  </footer>
</body>

</html>
