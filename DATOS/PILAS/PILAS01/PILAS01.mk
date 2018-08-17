##
## Auto Generated makefile by CodeLite IDE
## any manual changes will be erased      
##
## Debug
ProjectName            :=PILAS01
ConfigurationName      :=Debug
WorkspacePath          :=/Users/mr.blissfulgrin/Documents/UAH_2017:2018/DATOS/PILAS
ProjectPath            :=/Users/mr.blissfulgrin/Documents/UAH_2017:2018/DATOS/PILAS/PILAS01
IntermediateDirectory  :=./Debug
OutDir                 := $(IntermediateDirectory)
CurrentFileName        :=
CurrentFilePath        :=
CurrentFileFullPath    :=
User                   :=Juan Casado Ballesteros
Date                   :=10/10/2017
CodeLitePath           :="/Users/mr.blissfulgrin/Library/Application Support/codelite"
LinkerName             :=/usr/bin/g++
SharedObjectLinkerName :=/usr/bin/g++ -dynamiclib -fPIC
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
ObjectsFileList        :="PILAS01.txt"
PCHCompileFlags        :=
MakeDirCommand         :=mkdir -p
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
AR       := /usr/bin/ar rcu
CXX      := /usr/bin/g++
CC       := /usr/bin/gcc
CXXFLAGS :=  -g -O0 -Wall $(Preprocessors)
CFLAGS   :=  -g -O0 -Wall $(Preprocessors)
ASFLAGS  := 
AS       := /usr/bin/as


##
## User defined environment variables
##
CodeLiteDir:=/Applications/codelite.app/Contents/SharedSupport/
Objects0=$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IntermediateDirectory)/Pila.cpp$(ObjectSuffix) $(IntermediateDirectory)/Nodo.cpp$(ObjectSuffix) 



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
	@test -d ./Debug || $(MakeDirCommand) ./Debug


$(IntermediateDirectory)/.d:
	@test -d ./Debug || $(MakeDirCommand) ./Debug

PreBuild:


##
## Objects
##
$(IntermediateDirectory)/main.cpp$(ObjectSuffix): main.cpp $(IntermediateDirectory)/main.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017:2018/DATOS/PILAS/PILAS01/main.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/main.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/main.cpp$(DependSuffix): main.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/main.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/main.cpp$(DependSuffix) -MM main.cpp

$(IntermediateDirectory)/main.cpp$(PreprocessSuffix): main.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/main.cpp$(PreprocessSuffix) main.cpp

$(IntermediateDirectory)/Pila.cpp$(ObjectSuffix): Pila.cpp $(IntermediateDirectory)/Pila.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017:2018/DATOS/PILAS/PILAS01/Pila.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Pila.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Pila.cpp$(DependSuffix): Pila.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Pila.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Pila.cpp$(DependSuffix) -MM Pila.cpp

$(IntermediateDirectory)/Pila.cpp$(PreprocessSuffix): Pila.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Pila.cpp$(PreprocessSuffix) Pila.cpp

$(IntermediateDirectory)/Nodo.cpp$(ObjectSuffix): Nodo.cpp $(IntermediateDirectory)/Nodo.cpp$(DependSuffix)
	$(CXX) $(IncludePCH) $(SourceSwitch) "/Users/mr.blissfulgrin/Documents/UAH_2017:2018/DATOS/PILAS/PILAS01/Nodo.cpp" $(CXXFLAGS) $(ObjectSwitch)$(IntermediateDirectory)/Nodo.cpp$(ObjectSuffix) $(IncludePath)
$(IntermediateDirectory)/Nodo.cpp$(DependSuffix): Nodo.cpp
	@$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) -MG -MP -MT$(IntermediateDirectory)/Nodo.cpp$(ObjectSuffix) -MF$(IntermediateDirectory)/Nodo.cpp$(DependSuffix) -MM Nodo.cpp

$(IntermediateDirectory)/Nodo.cpp$(PreprocessSuffix): Nodo.cpp
	$(CXX) $(CXXFLAGS) $(IncludePCH) $(IncludePath) $(PreprocessOnlySwitch) $(OutputSwitch) $(IntermediateDirectory)/Nodo.cpp$(PreprocessSuffix) Nodo.cpp


-include $(IntermediateDirectory)/*$(DependSuffix)
##
## Clean
##
clean:
	$(RM) -r ./Debug/


