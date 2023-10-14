import React from 'react'

const Delivery = () => {
  return (
    <div className='w-full bg-white py-16 px-4'>
         <h3 className='text-orange-500 font-bold text-2xl text-center'>Quick Delivery App</h3>
         <div className='w-[1520px] mx-auto grid md:grid-cols-2'>
            <img  className='w-[550px] mx-auto my-4' 
            src="https://res.cloudinary.com/ehizeex-shop/image/upload/v1672672076/NetflixApp/FC_two_phones.6ec9a842f905769677f9_m91off.webp" alt="" />
         
         <div className='flex flex-col justify-center'>
             <p className='text-[#00df9a] font-bold'>Get the App</p>
             <h1 className='md:text-4xl sm:text-3xl text-2xl font-bold py-2'>Limitless Convience on-demand</h1>
            <p className='text-justify pe-20'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Recusandae beatae possimus blanditiis cum ab neque aliquam distinctio eos, reiciendis, eius architecto rerum accusantium, maiores nam voluptatem a ut! Veritatis, nam?
            Porro inventore recusandae mollitia sunt provident, modi, tempore corrupti nihil at ea sed consectetur minima eaque nulla cum aut rem delectus dicta natus dolorem facilis quia eligendi dolor! Facere, maxime.
            Omnis dolore eligendi amet iste nostrum suscipit? Magnam totam quos culpa impedit nam corporis sapiente incidunt amet ipsum explicabo qui, at aliquam, ratione non debitis exercitationem, soluta in. Modi, voluptates.</p>
            <button className='bg-black text-[#00df9a] w-[200px] rounded-md font-medium my-6 mx-auto md:mx-o py-3'>Get Started</button>
         </div>
         </div>
        
    </div> 
  )
}

export default Delivery