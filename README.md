# Talp Smart Teacher - تطبيق ذكي للمعلمين

## نظرة عامة
تطبيق أندرويد متكامل يساعد المعلمين والطلاب على إدارة العملية التعليمية بكفاءة.

## المميزات الرئيسية

### نظام تسجيل الدخول المزدوج
- تسجيل دخول المعلم: اسم مستخدم + كلمة مرور (كلمة المرور الافتراضية: 1234)
- تسجيل دخول الطالب: كود دراسي 4 أرقام
- حفظ تلقائي للبيانات باستخدام DataStore

### لوحة تحكم المعلم
1. **إدارة الطلاب**: إضافة وحذف وتعديل بيانات الطلاب
2. **الواجبات والوظائف**: إنشاء وإدارة الواجبات مع إرسال إشعارات
3. **البرنامج الأسبوعي**: جدول أسبوعي للحصص (6 حصص × 5 أيام)
4. **العلامات**: إدارة علامات الطلاب بأقسام متعددة
5. **برنامج الامتحانات**: جدول الامتحانات والمذاكرات للفصلين
6. **الملاحظات**: إضافة ملاحظات فردية للطلاب

### لوحة الطالب
1. **الواجبات**: عرض الواجبات المرسلة
2. **جدول الأسبوع**: عرض برنامج الحصص (للقراءة فقط)
3. **العلامات**: عرض علاماتك الشخصية
4. **برنامج الامتحانات**: عرض جدول الامتحانات
5. **الملاحظات**: عرض الملاحظات من المعلم

## المتطلبات التقنية

### المكتبات المستخدمة
- **Jetpack Compose**: واجهة المستخدم
- **Room Database**: قاعدة البيانات المحلية
- **WorkManager**: المزامنة والعمليات الدورية
- **DataStore**: حفظ ��فضيلات التطبيق
- **Coroutines**: البرمجة غير المتزامنة
- **Navigation Compose**: التنقل بين الشاشات
- **Material3**: تصميم Material Design 3

### متطلبات النظام
- Android SDK 24+ (Android 7.0)
- Target SDK 34 (Android 14)
- Kotlin 1.9.0+
- Java 17

## البنية المعمارية

```
app/src/main/java/com/talp/smartteacher/
├── MainActivity.kt
├── TalpApplication.kt
├── database/
│   ├── entities/
│   │   ├── Teacher.kt
│   │   ├── Student.kt
│   │   ├── Subject.kt
│   │   ├── Grade.kt
│   │   ├── Homework.kt
│   │   ├── Note.kt
│   │   ├── Schedule.kt
│   │   └── Exam.kt
│   ├── dao/
│   │   ├── TeacherDao.kt
│   │   ├── StudentDao.kt
│   │   ├── SubjectDao.kt
│   │   ├── GradeDao.kt
│   │   ├── HomeworkDao.kt
│   │   ├── NoteDao.kt
│   │   ├── ScheduleDao.kt
│   │   └── ExamDao.kt
│   └── TalpDatabase.kt
├── data/
│   └── repository/
│       ├── TeacherRepository.kt
│       ├── StudentRepository.kt
│       ├── SubjectRepository.kt
│       ├── GradeRepository.kt
│       ├── HomeworkRepository.kt
│       ├── NoteRepository.kt
│       ├── ScheduleRepository.kt
│       └── ExamRepository.kt
├── ui/
│   ├── screens/
│   │   ├── LoginScreen.kt
│   │   ├── TeacherDashboard.kt
│   │   ├── StudentDashboard.kt
│   │   └── SettingsScreen.kt
│   ├── components/
│   │   └── CommonComponents.kt
│   ├── viewmodel/
│   │   ├── TeacherViewModel.kt
│   │   ├── StudentViewModel.kt
│   │   ├── GradeViewModel.kt
│   │   ├── HomeworkViewModel.kt
│   │   ├── NoteViewModel.kt
│   │   ├── SubjectViewModel.kt
│   │   ├── ScheduleViewModel.kt
│   │   └── ExamViewModel.kt
│   └── theme/
│       ├── Color.kt
│       ├── Type.kt
│       └── Shape.kt
├── utils/
│   ├── NotificationHelper.kt
│   ├── DataStoreManager.kt
│   └── DateTimeUtils.kt
├── navigation/
│   ├── TalpRoute.kt
│   └── TalpNavigation.kt
└── services/
    └── NotificationService.kt
```

## نظام الألوان (Material3 Gradient)
- **اللون الأساسي**: #2E7D32 (أخضر)
- **اللون الثانوي**: #1B5E20 (أخضر داكن)
- **اللون الثالث**: #F5E6C4 (بيج)
- **الخلفية الفاتحة**: #FEF5E7 (بيج فاتح جداً)

## نظام الإشعارات

يتم إرسال إشعارات عند:
1. **الواجبات**: عند إضافة واجب جديد
2. **العلامات**: عند إضافة علامة جديدة للطالب
3. **الملاحظات**: عند إضافة ملاحظة للطالب

كل إشعار يظهر في:
- شريط الإشعارات في الجهاز
- داخل التطبيق (إن أمكن)

## اللغة والدعم
- **اللغة الأساسية**: العربية (RTL)
- **دعم RTL كامل**: في جميع الشاشات والمكونات
- **جميع النصوص**: مخزنة في strings.xml

## المواد الافتراضية

**اللغة العربية** تنقسم إلى:
- قراءة (Reading)
- إملاء (Dictation)
- نشيد (Song)

يمكن للمعلم إضافة مواد أخرى وتخصيص أقسامها.

## الفوتر الموحد
في أسفل كل صفحات البرنامج يظهر:
"تصميم الأستاذ: محمد جيلو"

## التثبيت والبناء

### متطلبات البناء
```bash
./gradlew build
```

### تشغيل على الجهاز
```bash
./gradlew installDebug
```

### بناء APK
```bash
./gradlew assembleRelease
```

## قاعدة البيانات

التطبيق يستخدم 8 جداول رئيسية:
1. Teachers - بيانات المعلمين
2. Students - بيانات الطلاب
3. Subjects - المواد الدراسية
4. Homework - الواجبات
5. Grades - العلامات
6. Notes - الملاحظات
7. Schedule - الجدول الأسبوعي
8. Exams - برنامج الامتحانات

## الترخيص
© 2026 - تصميم الأستاذ: محمد جيلو

## الإصدار
**الإصدار**: 1.0.0
**تاريخ الإنشاء**: 2026-08-11
