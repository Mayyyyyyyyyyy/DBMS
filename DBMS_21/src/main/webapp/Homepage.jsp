<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>


<head>
 
  

  <title>求職平台</title>
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

    /* CSS 程式碼 */
  </style>
</head>

<body>
  <div class="header">
    <a class="logo" href="#">
        <img src="1200x630wa.png" alt="">
    </a>
    <nav>
        <a href="Homepage.jsp">首頁</a>
        <a href="Resume.jsp">履歷管理</a>
        <a href="Login.jsp">登入</a>
        <a href="Register.jsp">註冊</a>
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
    
    
    
        <h2>篩選條件</h2>
        <!-- 篩選條件表單 -->
      </div>
    </div>
    <div>
      <!-- 搜尋結果列表 -->
      <!-- 每個搜尋結果顯示：標題、公司、地點、私訊按鈕、收藏按鈕 -->
    </div>
  </main>

  <footer>
    <p>版權聲明 | 聯絡我們</p>
  </footer>
</body>

</html>
