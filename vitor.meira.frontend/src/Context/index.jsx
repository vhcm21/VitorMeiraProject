import React, {useState} from "react"

const SamuraiContext = React.createContext()
export const SamuraiProvider = (props) => {

    const {children} = props

    const [samurais, setSamurais] = useState ([]); 

    return (<SamuraiContext.Provider value={{samurais, setSamurais}}>
            {children}
        </SamuraiContext.Provider>)

}




export default SamuraiContext