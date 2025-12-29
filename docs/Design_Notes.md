# Design Notes

## Why ArrayList instead of array
- Dynamic sizing avoids manual resizing and copying common with arrays.
- Efficient add/remove operations and predicate-based removal simplify service logic.
- Rich API (streams, iteration) improves readability over manual index management.
- Used for in-memory repositories in services:
  - Courses: [CourseService.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/service/CourseService.java#L15)
  - Students: [StudentService.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/service/StudentService.java#L14)
  - Enrollments: [EnrollmentService.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/service/EnrollmentService.java#L16)

## Where static members were used and why
- ID generation counters and methods:
  - [IdGenerator.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/util/IdGenerator.java) keeps shared counters to ensure unique IDs across the app.
- Validation utilities:
  - [InputValidator.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/util/InputValidator.java) exposes stateless checks as static methods for convenience and clarity.
- UI flow flag:
  - [Main.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/ui/Main.java#L17) uses a static exit flag to control loop termination from menu handlers.
- Rationale: shared state (IDs) and stateless utilities (validation) are naturally static, avoiding unnecessary object instantiation and enabling simple global access.

## Where inheritance was used and what it provided
- Common person model:
  - Base class: [Person.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/entity/Person.java) encapsulates identity, name/email, active flag, and display name.
  - Subclasses: [Student.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/entity/Student.java), [Trainer.java](file:///Users/rishavchoudhary/LearnTrack/src/main/com/learntrack/studentmanagement/entity/Trainer.java) add specific fields.
- Benefits:
  - Code reuse for shared attributes and behavior (e.g., `getDisplayName`).
  - Clear domain hierarchy that is easy to extend.
  - Reduced duplication and improved maintainability.

## Clean Code Practices Followed
- Methods and services keep focused responsibilities; UI operations are grouped by menu.
- Meaningful names throughout services and entities; minor typos corrected in the UI.
- Validation and ID generation separated into dedicated utility classes for clarity.

