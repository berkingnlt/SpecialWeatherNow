document.addEventListener("DOMContentLoaded", function() {weather.fetchWeather("İstanbul");});
const weather = {
  apiKey: "b0d254c0fe1a7b96cd6b41221d03f7c7",
  
  fetchWeather: function (city) {
    fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${this.apiKey}&lang=tr`
    )
      .then((response) => {
        if (!response.ok) {
          alert("Hava durumuna ait bir bilgi bulunmadı.");
          throw new Error("Hava durumuna ait bir bilgi bulunmadı.");
        }
        return response.json();
      })
      .then((data) => {
        this.displayWeather(data);
        this.fetchHourlyForecast(data.coord.lat, data.coord.lon);
        this.displayWarnings(data);
      });
  },
  
  displayWeather: function (data) {
    const { name } = data;
    const { icon, description } = data.weather[0];
    const { temp, humidity } = data.main;
    const { speed } = data.wind;

    document.querySelector(".city").innerText = `${name}'da Hava Durumu`;
    document.querySelector(".icon").src = `https://openweathermap.org/img/wn/${icon}.png`;
    document.querySelector(".description").innerText = description;
    document.querySelector(".temp").innerText = `${temp}°C`;
    document.querySelector(".humidity").innerText = `Nem: ${humidity}%`;
    document.querySelector(".wind").innerText = `Rüzgar Hızı: ${speed} km/h`;
    document.querySelector(".weather").classList.remove("loading");
    document.body.style.backgroundImage = `url('https://source.unsplash.com/1600x900/?${name}')`;
  },


  fetchHourlyForecast: function (lat, lon) {
    fetch(
      `https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&units=metric&appid=${this.apiKey}&lang=tr`
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error("Saatlik hava durumu tahmini alınamadı.");
        }
        return response.json();
      })
      .then((data) => {
        this.displayHourlyForecast(data.list);
      })
      .catch((error) => {
        console.error("Error fetching hourly forecast:", error);
      });
  },

  displayHourlyForecast: function (hourlyData) {
    const hourlyForecastDiv = document.getElementById("hourly-forecast");
    hourlyForecastDiv.innerHTML = ""; 

    const next24Hours = hourlyData.slice(0, 8); 

    next24Hours.forEach((item) => {
      const dateTime = new Date(item.dt * 1000); 
      const hour = dateTime.getHours();
      const temperature = item.main.temp;
      const iconCode = item.weather[0].icon;
      const iconUrl = `https://openweathermap.org/img/wn/${iconCode}.png`;

      const hourlyItemHtml = `
        <div class="hourly-item">
            <span>${hour}:00</span>
            <img src="${iconUrl}" alt="Hourly Weather Icon">
            <span>${temperature}°C</span>
        </div>
      `;

      hourlyForecastDiv.innerHTML += hourlyItemHtml;
    });
  },

  displayWarnings: function (data) {
    const { description } = data.weather[0];
    const { speed } = data.wind;
    const { temp } = data.main;
    let warnings = [];

    if (speed > 7.5) {
      warnings.push("Hava rüzgarlı!");
    }
    if (description.includes("yağmur")) {
      warnings.push("Yağmurlu hava! Yanınıza şemsiye almayı unutmayın.");
    }
    if (description.includes("güneş")) {
      warnings.push("Güneşli hava! Yanınıza şapka veya güneş gözlüğü almayı unutmayın.");
    }
    if (temp < 10) {
      warnings.push("Hava soğuk! Üstüne ceket almalısın.");
    }

    if (warnings.length === 0) {
      warnings.push("Hava gayet güzel!");
    }

    const warningsContainer = document.getElementById("uyarı");
    if (warningsContainer) {
      const warningsTitle = document.createElement("h2");
      warningsTitle.textContent = "Uyarılar";
      warningsContainer.innerHTML = ""; 
      warningsContainer.appendChild(warningsTitle);
      warnings.forEach(warning => {
        const warningMessageElement = document.createElement("p");
        warningMessageElement.textContent = warning;
        warningsContainer.appendChild(warningMessageElement);
      });
    } else {
      console.error("Uyarılar için hedef öğe bulunamadı.");
    }
  },
  

  search: function () {
    this.fetchWeather(document.querySelector(".search-bar").value);
  },
};

document.querySelector(".search button").addEventListener("click", function () {
  weather.search();
});

document.querySelector(".search-bar").addEventListener("keyup", function (event) {
  if (event.key === "Enter") {
    weather.search();
  }
});

weather.fetchWeather("İstanbul");
