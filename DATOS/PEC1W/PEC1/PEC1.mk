##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=PEC1
ConfigurationName      :=Debug
WorkspacePath          :=//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W
ProjectPath            :=//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=mr.blissfulgrin
Date                   :=03/11/2017
CodeLitePath           :="C:/Program Files/CodeLite"
LinkerName             :=C:/TDM-GCC-64/bin/g++.exe
SharedObjectLinkerName :=C:/TDM-GCC-64/bin/g++.exe -shared -fPIC
ObjectSuffix           :=.o
DependSuffix           :=.o.d
PreprocessSuffix       :=.i
DebugSwitch            :=-g 
IncludeSwitch          :=-I
LibrarySwitch          :=-l
OutputSwitch           :=-o 
LibraryPathSwitch      :=-L
PreprocessorSwitch     :=-D
SourceSwitch           :=-c 
OutputFile             :=$(IntermediateDirectory)/$(ProjectName)
Preprocessors          :=
ObjectSwitch           :=-o 
ArchiveOutputSwitch    := 
PreprocessOnlySwitch   :=-E
ObjectsFileList        :="PEC1.txt"
PCHCompileFlags        :=
MakeDirCommand         :=makedir
RcCmpOptions           := 
RcCompilerName         :=C:/TDM-GCC-64/bin/windres.exe
LinkOptions            :=  
IncludePath            :=  $(IncludeSwitch). $(IncludeSwitch). 
IncludePCH             := 
RcIncludePath          := 
Libs                   := 
ArLibs                 :=  
LibPath                := $(LibraryPathSwitch). 

##
## Common variables
## AR, CXX, CC, AS, CXXFLAGS and CFLAGS can be overriden using an environment variables
##
AR       := C:/TDM-GCC-64/bin/ar.exe rcu
CXX      := C:/TDM-GCC-64/bin/g++.exe
CC       := C:/TDM-GCC-64/bin/gcc.exe
CXXFLAGS :=  -g -O0 -Wall $(Preprocessors)
CFLAGS   :=  -g -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := C:/TDM-GCC-64/bin/as.exe


##
## User defined environment variables
##
CodeLiteDir:=C:\Program Files\CodeLite
Objects0=$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) $(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) $(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) $(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) $(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) 



Objects=$(Objects0) 

##
## Main Build Targets 
##
.PHONY: all clean PreBuild PrePreBuild PostBuild MakeIntermediateDirs
all: $(OutputFile)

$(OutputFile): $(IntermediateDirectory)/.d $(Objects) 
	@$(MakeDirCommand) $(@D)
	@echo "" > $(IntermediateDirectory)/.d
	@echo $(Objects0)  > $(ObjectsFileList)
	$(LinkerName) $(OutputSwitch)$(OutputFile) @$(ObjectsFileList) $(LibPath) $(Libs) $(LinkOptions)

MakeIntermediateDirs:
	@$(MakeDirCommand) "./Debug"


$(IntermediateDirectory)/.d:
	@$(MakeDirCommand) "./Debug"

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/main.cpp$(ObjectSuffix): main.cpp $(IntermediateDirectory)/main.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/main.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/main.cpp$(DependSuffix): main.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/main.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/main.cpp$(DependSuffix) -MM main.cpp

$(IntermediateDirectory)/main.cpp$(PreprocessSuffix): main.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/main.cpp$(PreprocessSuffix) main.cpp

$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix): Cliente.cpp $(IntermediateDirectory)/Cliente.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/Cliente.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Cliente.cpp$(DependSuffix): Cliente.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Cliente.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Cliente.cpp$(DependSuffix) -MM Cliente.cpp

$(IntermediateDirectory)/Cliente.cpp$(PreprocessSuffix): Cliente.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Cliente.cpp$(PreprocessSuffix) Cliente.cpp

$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix): Cola.cpp $(IntermediateDirectory)/Cola.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/Cola.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Cola.cpp$(DependSuffix): Cola.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Cola.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Cola.cpp$(DependSuffix) -MM Cola.cpp

$(IntermediateDirectory)/Cola.cpp$(PreprocessSuffix): Cola.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Cola.cpp$(PreprocessSuffix) Cola.cpp

$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix): Gestor.cpp $(IntermediateDirectory)/Gestor.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/Gestor.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Gestor.cpp$(DependSuffix): Gestor.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Gestor.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Gestor.cpp$(DependSuffix) -MM Gestor.cpp

$(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix): Gestor.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Gestor.cpp$(PreprocessSuffix) Gestor.cpp

$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix): Lista.cpp $(IntermediateDirectory)/Lista.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/Lista.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Lista.cpp$(DependSuffix): Lista.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Lista.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Lista.cpp$(DependSuffix) -MM Lista.cpp

$(IntermediateDirectory)/Lista.cpp$(PreprocessSuffix): Lista.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Lista.cpp$(PreprocessSuffix) Lista.cpp

$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix): NodoLista.cpp $(IntermediateDirectory)/NodoLista.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/NodoLista.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoLista.cpp$(DependSuffix): NodoLista.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoLista.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoLista.cpp$(DependSuffix) -MM NodoLista.cpp

$(IntermediateDirectory)/NodoLista.cpp$(PreprocessSuffix): NodoLista.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoLista.cpp$(PreprocessSuffix) NodoLista.cpp

$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix): NodoCola.cpp $(IntermediateDirectory)/NodoCola.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "//Mac/Home/Documents/UAH_2017_2018/DATOS/PEC1W/PEC1/NodoCola.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/NodoCola.cpp$(DependSuffix): NodoCola.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/NodoCola.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/NodoCola.cpp$(DependSuffix) -MM NodoCola.cpp

$(IntermediateDirectory)/NodoCola.cpp$(PreprocessSuffix): NodoCola.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/NodoCola.cpp$(PreprocessSuffix) NodoCola.cpp


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


