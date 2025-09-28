# repo_practice

A practice project demonstrating the implementation of **Retrofit** with all HTTP methods (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`), custom **Interceptors** such as **AuthInterceptor** and **LoggingInterceptor**, and **Unit Testing** using **MockWebServer** and **Mockito**.

---

## 🚀 Features
- **HTTP Methods Implementation**
  - `GET` → Fetch data from API
  - `POST` → Send data to API
  - `PUT` → Update entire resource
  - `PATCH` → Partially update resource
  - `DELETE` → Remove resource from server
- **Custom Interceptors**
  - **AuthInterceptor** → Automatically attaches authentication tokens to requests.
  - **LoggingInterceptor** → Logs API requests and responses for easier debugging.
- **Unit Testing**
  - Tests network calls using **MockWebServer** to simulate real API responses.
  - Ensures API layer is **tested offline**, with no dependency on the actual backend.
- **Branch-wise Learning**
  - Each concept is implemented in a **separate branch** for better understanding and practice.

---

## 🗂️ Branch Structure
| Branch Name                | Description |
|----------------------------|-------------|
| `feature/post-posts`       | Implementation of `POST` requests |
| `feature/delete-posts`     | Implementation of `DELETE` requests |
| `feature/put-patch-posts`  | Implementation of `PUT` and `PATCH` requests |
| `feature/interceptor`      | Added `AuthInterceptor` and `LoggingInterceptor` |
| `feature/interceptor`       | Added **unit tests** for Retrofit APIs using `MockWebServer` and `Mockito` |

---

## 🛠️ Tech Stack
- **Language:** Kotlin
- **Networking:** Retrofit, OkHttp
- **Coroutines:** For async API calls
- **Testing:** JUnit, Mockito, MockWebServer
- **Gradle Version Catalog:** Using `libs.versions.toml`

---
