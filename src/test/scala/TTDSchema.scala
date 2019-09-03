case class Data(
                 TimestampUtc: String,
                 Name: String,
                 TtlInMinutes: String
               )

case class Item(
                 TDID: String,
                 Data: Seq[Data]
               )


class TTDSchema(
                 val DataProviderId: String,
                 val Items: Seq[Item]
               ) extends Serializable

