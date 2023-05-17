<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>個人履歷</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    h1 {
      color: #333;
    }
    label {
      display: block;
      margin-bottom: 5px;
    }
    input, textarea {
      width: 100%;
      padding: 5px;
      margin-bottom: 10px;
    }
    button {
      padding: 10px 20px;
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <header>
    <h1>個人履歷</h1>
  </header>
  
  <form>
    <h2>基本資訊</h2>
    <label for="name">姓名：</label>
    <input type="text" id="name" name="name">
    <label for="email">聯絡方式：</label>
    <input type="email" id="email" name="email">
    <label for="phone">電話號碼：</label>
    <input type="tel" id="phone" name="phone">
    <label for="address">地址：</label>
    <textarea id="address" name="address"></textarea>
  
    <h2>教育背景</h2>
    <label for="degree">學歷：</label>
    <input type="text" id="degree" name="degree">
    <label for="university">學校：</label>
    <input type="text" id="university" name="university">
    <label for="gradYear">畢業年份：</label>
    <input type="text" id="gradYear" name="gradYear">
    <label for="department">科系：</label>
    <select id="department" name="department">
      <option value="">請選擇科系</option>
      <option value="計算機科學">計算機科學</option>
      <option value="資訊管理">資訊管理</option>
      <option value="電子工程">電子工程</option>
      <option value="商業管理">商業管理</option>
    </select>
  
    <h2>工作經驗</h2>
    <label for="company">公司名稱：</label>
    <input type="text" id="company" name="company">
    <label for="position">職位：</label>
    <input type="text" id="position" name="position">
    <label for="workDates">日期：</label>
    <input type="text" id="workDates" name="workDates">
    <label for="jobDescription">工作描述：</label>
    <textarea id="jobDescription" name="jobDescription"></textarea>
  
    <h2>技能</h2>
    <label for="skills">技能：</label>
    <textarea id="skills" name="skills"></textarea>
  
    <button type="submit">儲存</button>
  </form>
  
  <footer>
    <p>&copy; 2023 Your Name</p>
  </footer>
</body>
</html>
