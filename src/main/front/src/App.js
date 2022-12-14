import logo from './logo.svg';
import './App.css';
import React, {useState} from 'react';
import axios from 'axios';

function App() {
  const [file, setFile] = useState()

  function handleChange(event) {
    setFile(event.target.files[0])
  }
  
  function handleSubmit(event) {
    event.preventDefault()
    const url = 'http://localhost:8080/uploadFile';
    const formData = new FormData();
    formData.append('file', file);
    formData.append('fileName', file.name);
    const config = {
      headers: {
        'content-type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      console.log(response.data);
    });

  }

  return (
    <div className="App">
        <form onSubmit={handleSubmit} encType="multipart/form-data">
          <h1>React File Upload</h1>
          <input type="file" name="uploadFile" onChange={handleChange}/>
          <button type="submit">Upload</button>
        </form>
    </div>
  );
}

export default App;
