# Personal Productivity App - Backend

A Spring Boot application designed to help users organize their lives by managing personal diaries, financial diaries, tracking goals, monitoring habits, and maintaining daily to-do lists. The app emphasizes **privacy**, **security**, and **ease of use**, ensuring users can securely manage their personal data.

---

## Features

### 1. Personal Diaries
- Create, read, update, and delete personal diaries.
- Each personal diary can track:
    - **Goals**: Users can set personal goals (e.g., read books, fitness goals) and monitor their progress.
    - **Habit Tracker**: Track habits (e.g., workout routines, reading habits) and the days they were completed.
    - **To-Do Lists**: Maintain daily to-do lists with tasks and their status (e.g., Done, Pending).

- **CRUD Operations**: Personal diaries and their associated entities (goals, habit trackers, to-do lists) can be manipulated using standard Create, Read, Update, and Delete operations.
- **Privacy**: Diaries are private and can only be accessed and modified by the user who created them.

### 2. Financial Diaries
- Create, read, update, and delete financial diaries.
- Each financial diary can track **entries**, **recurring expenses**, and **saving goals**.
- Diaries are private and can only be accessed by the user who created them.

    - **Entries**: Users can track individual financial transactions (expenses, income).
    - **Recurring Expenses**: Allows users to track regular monthly/recurring expenses (e.g., rent, subscriptions).
    - **Saving Goals**: Users can set specific financial goals (e.g., save for a vacation) and track their progress.

- **CRUD Operations**: Financial diary and its associated entities (entries, recurring expenses, saving goals) can be manipulated using standard Create, Read, Update, and Delete operations.

---

## Technologies Used
- **Java**
- **Spring Boot**
    - Spring Security for authentication and authorization
    - Spring Data JPA for database access
- **MapStruct** for DTO and entity mapping
- **Lombok** for reducing boilerplate code
- **PostgreSQL
- **Maven** for dependency management

---

## Security
1. **Authentication**:
    - Implemented with Spring Security.
    - Users must log in to access any endpoint.

2. **Authorization**:
    - Users can only interact with resources they own (e.g., financial diaries, entries, goals).
    - Ownership is validated for all CRUD operations.

3. **Custom Exceptions**:
    - Unauthorized users receive meaningful error messages with proper HTTP status codes.

4. **Endpoint Protection**:
    - All endpoints are secured to prevent unauthorized access.

## Future Enhancements