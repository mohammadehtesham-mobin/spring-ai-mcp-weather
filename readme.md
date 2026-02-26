# Spring Boot MCP Weather Server

A **Spring Boot** based **Model Context Protocol (MCP) server** that exposes weather information as a structured tool for MCP-compatible clients.

This project demonstrates how to build a **domain-specific MCP server** using Spring Boot and integrate external weather services to provide structured, machine-consumable weather data for AI agents or orchestration systems.

---

## 🚀 Features

* Spring Boot based MCP server
* MCP tool implementation for weather lookup
* Fetch weather information for cities within the NSW region
* Structured responses suitable for AI agent consumption
* Clean layered architecture (Controller → Service → Tool)
* Easily extensible to add more MCP tools or domains

---

## 🛠 Tech Stack

* Java 17
* Spring Boot
* Spring AI MCP
* Maven
* REST APIs

---

## 📂 Project Structure

```
src/main/java/com/nagp/mcpserver/weather
│
├── controller    # REST Controllers
├── service       # Business logic
├── tool          # MCP tool implementations
├── config        # Configuration classes
└── model         # Request / Response models
```

---

## ⚙️ Prerequisites

* Java 17
* Maven 3.8+
* IntelliJ IDEA (recommended)

---

## 🔧 Configuration

Update `application.properties`:

```
app.user-agent=your-application-name
server.port=8090
```

---

## ▶️ Running the Application

### Using Maven

```
mvn clean install
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8090
```

MCP clients can connect to the server and invoke the exposed weather tools.

---

## 🔌 MCP Server Use Case

This project implements a **Model Context Protocol (MCP) server** that exposes weather data as a tool/service which can be consumed by MCP-compatible clients.

The server enables:

* Structured weather data access via MCP
* Tool-style integration for AI agents or orchestration layers
* Standardized interface for city-based weather queries
* Simple extension for additional tools or domains

### Available MCP Tool

* **Tool Name:** `getWeather`
* **Input:** City name (NSW region)
* **Output:** Structured weather data suitable for agent consumption

---

## 🔮 Possible Extensions

* Add additional MCP tools (forecast, air quality, alerts)
* Integrate with AI agents using MCP tool calling
* Add authentication and rate limiting
* Support multiple weather providers
* Containerize the application for deployment
* Add CI pipeline using GitHub Actions

---

## 📌 Repository Info

**Package Name:**
`com.nagp.mcpserver.weather`

**Repository:**
spring-ai-mcp-weather

---

## 👤 Author

**Mohammad Ehtesham Mobin**

---

## ⭐ If you find this useful

Give this repository a star and feel free to fork and extend it!
