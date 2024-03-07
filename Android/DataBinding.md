for databinding
1. in layout.xml create variable of mainActivity and data classes
2. in java file inflate layout and create DataBinding obj
3. create obj of data class and pass data in constructor
4. pass data class obj to layout.xml all variables and set mainActivity and data using binding obj

for two way data binding
5. extend data class from baseobservable and assign @Bindable to variable which u want to bind. also provide setter in it
6. add notifyPopertyChanged(BR.variableName) in setter(BR is obj created of baseobservable)
e.g. 
class UserData : BaseObservable() {
    @Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
}

for viewModel
7. create new class extend by viewmodel add var and methods
class ViewModelData : ViewModel() {
    private var dataValue:Int=0
    fun getDataValue():Int{
        return dataValue
    }

}

8. in java main class create viewmodel class instance and get value from that
e.g.
 viewModelData=ViewModelProvider(this).get(ViewModelData::class.java)
        binding.showViewModelTxt.text=viewModelData.getDataValue().toString()

---------------------------------------------------------------------------------
9. for livedata create instance of mutableLiveeData and assing to that var and in init block assign its value
  var dataValue=MutableLiveData<Int>()
    init {
        dataValue.value=0
    }

    make sure to add binding.lifecycleOwner=this // for twowaydatabinding and livedata