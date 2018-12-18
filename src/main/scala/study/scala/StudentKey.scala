package src.study.scala

case class StudentKey(grade: String, score: Int)

object StudentKey {
  implicit def orderingByGradeStudentScore[A <: StudentKey]: Ordering[A] = {
    Ordering.by(fk => (fk.grade, fk.score * -1))
  }
}
