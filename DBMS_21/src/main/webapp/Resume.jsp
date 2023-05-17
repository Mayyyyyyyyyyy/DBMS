<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>�ӤH�i��</title>
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
    <h1>�ӤH�i��</h1>
  </header>
  
  <form>
    <h2>�򥻸�T</h2>
    <label for="name">�m�W�G</label>
    <input type="text" id="name" name="name">
    <label for="email">�p���覡�G</label>
    <input type="email" id="email" name="email">
    <label for="phone">�q�ܸ��X�G</label>
    <input type="tel" id="phone" name="phone">
    <label for="address">�a�}�G</label>
    <textarea id="address" name="address"></textarea>
  
    <h2>�Ш|�I��</h2>
    <label for="degree">�Ǿ��G</label>
    <input type="text" id="degree" name="degree">
    <label for="university">�ǮաG</label>
    <input type="text" id="university" name="university">
    <label for="gradYear">���~�~���G</label>
    <input type="text" id="gradYear" name="gradYear">
    <label for="department">��t�G</label>
    <select id="department" name="department">
      <option value="">�п�ܬ�t</option>
      <option value="�p������">�p������</option>
      <option value="��T�޲z">��T�޲z</option>
      <option value="�q�l�u�{">�q�l�u�{</option>
      <option value="�ӷ~�޲z">�ӷ~�޲z</option>
    </select>
  
    <h2>�u�@�g��</h2>
    <label for="company">���q�W�١G</label>
    <input type="text" id="company" name="company">
    <label for="position">¾��G</label>
    <input type="text" id="position" name="position">
    <label for="workDates">����G</label>
    <input type="text" id="workDates" name="workDates">
    <label for="jobDescription">�u�@�y�z�G</label>
    <textarea id="jobDescription" name="jobDescription"></textarea>
  
    <h2>�ޯ�</h2>
    <label for="skills">�ޯ�G</label>
    <textarea id="skills" name="skills"></textarea>
  
    <button type="submit">�x�s</button>
  </form>
  
  <footer>
    <p>&copy; 2023 Your Name</p>
  </footer>
</body>
</html>
